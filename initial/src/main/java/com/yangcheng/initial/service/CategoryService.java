package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Integer categoryId, Category category);
    void deleteCategory(Integer categoryId);
    List<Category> getAllCategories();
    Category getCategoryById(Integer categoryId);
}
