package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.auth.AuthResponse;
import com.opencodex.jobportal.dto.auth.LoginRequest;
import com.opencodex.jobportal.dto.auth.RegisterRequest;
import com.opencodex.jobportal.dto.auth.UserResponse;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.enums.RoleEnum;
import com.opencodex.jobportal.repository.UserRepository;
import com.opencodex.jobportal.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register
    public AuthResponse register(RegisterRequest request) {

        // Verify email
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(false, "The email address is already registered.", null);
        }

        // Create user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(RoleEnum.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save data
        userRepository.save(user);

        return new AuthResponse(true, "User successfully registered", null);
    }

    // Login
    public AuthResponse login(LoginRequest request) {

        // Search User
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return new AuthResponse(false, "Invalid Credentials", null);
        }

        // Compare hashedPassword
        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return new AuthResponse(false, "Invalid Credentials", null);
        }

        String token = jwtUtil.generateToken(
          user.getId().toString(),
          user.getEmail(),
          user.getRole().name()
        );

        // JWT here
        return new AuthResponse(true, "Successful Login", token);
    }

    // DTO Public
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
