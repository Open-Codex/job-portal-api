package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.category.CategoryRequest;
import com.opencodex.jobportal.dto.category.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse getCategoryById(UUID id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(UUID id, CategoryRequest request);

    void deleteCategory(UUID id);
}
