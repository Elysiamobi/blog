package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
    List<Comment> findByPostId(Integer postId);
    List<Comment> findByUserId(Integer userId);
    Optional<Comment> findById(Integer commentId);
    void saveComment(Comment comment);
    void deleteComment(Integer commentId);
}

