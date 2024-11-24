package com.yangcheng.initial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    // 按类型分类
    List<Blog> findByCategory(BlogCategory category);

    // 按发布时间排序（默认降序）
    List<Blog> findAllByOrderByPublishDateDesc();

    // 按博客标题模糊搜索
    List<Blog> findByTitleContaining(String title);
}
