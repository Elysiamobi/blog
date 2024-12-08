package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Find a category by its name
    Category findByName(String name);

}

