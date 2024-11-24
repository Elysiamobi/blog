package com.yangcheng.initial.service;

import com.yangcheng.initial.Repository.CommentRepository;
import com.yangcheng.initial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    // 用户评论
    @Transactional
    public String addComment(String content, Integer blogId, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "用户不存在";
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBlog(new Blog());  // 创建一个 Blog 实例用于关联
        comment.setUser(userOptional.get());
        commentRepository.save(comment);
        return "评论添加成功";
    }

    // 获取某篇博客的所有评论
    public List<Comment> getCommentsByBlogId(Integer blogId) {
        return commentRepository.findByBlog_BlogId(blogId);
    }

    // 删除评论
    @Transactional
    public String deleteComment(Integer commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(commentId);
            return "评论删除成功";
        }
        return "评论不存在";
    }
}
