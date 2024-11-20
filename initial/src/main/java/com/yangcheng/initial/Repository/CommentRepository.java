package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Blog;
import com.yangcheng.initial.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlog(Blog blog);
}


