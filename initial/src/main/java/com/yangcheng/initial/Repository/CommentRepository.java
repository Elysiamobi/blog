package com.yangcheng.initial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 按博客 ID 查找评论
    List<Comment> findByBlog_BlogId(Integer blogId);

    // 删除某条评论
    void deleteById(Integer commentId);
}
