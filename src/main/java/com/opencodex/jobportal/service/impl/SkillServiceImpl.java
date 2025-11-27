package com.opencodex.jobportal.service.impl;

import com.opencodex.jobportal.dto.skill.SkillRequest;
import com.opencodex.jobportal.dto.skill.SkillResponse;
import com.opencodex.jobportal.entity.Skill;
import com.opencodex.jobportal.repository.SkillRepository;
import com.opencodex.jobportal.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public SkillResponse createSkill(SkillRequest request) {
        if (skillRepository.existsByName(request.getName())) {
            throw new RuntimeException("Skill already exists");
        }

        Skill skill = new Skill();
        skill.setName(request.getName());

        skillRepository.save(skill);

        return new SkillResponse(skill.getId(), skill.getName());
    }

    @Override
    public SkillResponse getSkillById(UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        return new SkillResponse(skill.getId(), skill.getName());
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillResponse(skill.getId(), skill.getName()))
                .toList();
    }

    @Override
    public SkillResponse updateSkill(UUID id, SkillRequest request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setName(request.getName());
        skillRepository.save(skill);

        return new SkillResponse(skill.getId(), skill.getName());
    }

    @Override
    public void deleteSkill(UUID id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found");
        }

        skillRepository.deleteById(id);
    }
}
