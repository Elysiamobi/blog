package com.yangcheng.initial.service.impl;

import com.yangcheng.initial.entity.Comment;
import com.yangcheng.initial.Repository.CommentRepository;
import com.yangcheng.initial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    @Override
    public List<Comment> findByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> findByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public Optional<Comment> findById(Integer commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public void saveComment(Comment comment) {
         commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
