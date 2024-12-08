package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findByName(String name);
    Optional<Category> findById(Integer categoryId);
    void saveCategory(Category category);
    void deleteCategory(Integer categoryId);

    List<Category> findAll();
}
