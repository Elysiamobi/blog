package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContaining(String name);  // 支持按名称模糊查询
//    List<Category> findBycategoryId(Integer categoryId);
}

