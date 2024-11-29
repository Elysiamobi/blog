package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPost(Integer postId);
    List<Comment> getCommentsByUser(Integer userId);
    Comment createComment(Comment comment);
    void deleteComment(Integer commentId);
}
