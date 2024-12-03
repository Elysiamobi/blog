package com.yangcheng.initial.service.impl;

import com.yangcheng.initial.entity.Comment;
import com.yangcheng.initial.Repository.CommentRepository;
import com.yangcheng.initial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByPost(Integer postId) {
        return commentRepository.findByPost_PostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUser(Integer userId) {
        return commentRepository.findByUser_UserId(userId);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Integer commentId, String content) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        existingComment.setContent(content);
        return commentRepository.save(existingComment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
