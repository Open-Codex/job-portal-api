package com.opencodex.jobportal.service.impl;

import com.opencodex.jobportal.dto.category.CategoryRequest;
import com.opencodex.jobportal.dto.category.CategoryResponse;
import com.opencodex.jobportal.entity.Category;
import com.opencodex.jobportal.repository.CategoryRepository;
import com.opencodex.jobportal.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = new Category();
        category.setName(request.getName());

        return toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        Category category = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found")
        );
        return toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return  repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found")
        );

        if (repository.existsByNameIgnoreCase(request.getName()) && !category.getName().equalsIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Category name already taken");
        }

        category.setName(request.getName());
        return toResponse(repository.save(category));
    }

    @Override
    public void deleteCategory(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }

        repository.deleteById(id);
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
