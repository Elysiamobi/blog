package com.yangcheng.initial.Repository;


import com.yangcheng.initial.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitleContaining(String title);
    List<Blog> findByCategory(Blog.Category category);
}
