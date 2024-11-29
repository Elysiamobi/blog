package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Comment;
import com.yangcheng.initial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 获取某篇文章的所有评论
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Integer postId) {
        return commentService.getCommentsByPost(postId);
    }

    // 获取某用户的所有评论
    @GetMapping("/user/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable Integer userId) {
        return commentService.getCommentsByUser(userId);
    }

    // 添加评论
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    // 删除评论
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
