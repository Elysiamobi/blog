package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Comment;
import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.CommentService;
import com.yangcheng.initial.utils.AuthorizationUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String listComments(Model model) {
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);
        return "comments/list";
    }

    @GetMapping("/new")
    public String showAddCommentForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "comments/form";
    }

    @PostMapping("/save")
    public String saveComment(@ModelAttribute Comment comment, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            // 用户未登录，重定向到登录页面
            return "redirect:/login";
        }

        // 设置评论的作者ID为当前登录用户的ID
        comment.setUserId(loggedInUser.getUserId());

        // 保存评论
        commentService.saveComment(comment);
        return "redirect:/comments";
    }

    @GetMapping("/edit/{id}")
    public String showEditCommentForm(@PathVariable Integer id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Comment> comment = commentService.findById(id);

        if (comment.isPresent()) {
            // 只有管理员或评论的作者才可以编辑
            if (AuthorizationUtil.canModify(loggedInUser, comment.get().getUserId())) {
                model.addAttribute("comment", comment.get());
                return "comments/form";  // 如果是管理员或评论的作者，可以编辑评论
            } else {
                return "error/unauthorized";  // 如果没有权限，返回未授权页面
            }
        } else {
            return "redirect:/comments";  // 如果评论不存在，重定向到评论列表页面
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Integer id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Comment> comment = commentService.findById(id);

        if (comment.isPresent()) {
            // 使用 AuthorizationUtil 检查权限，确保只有管理员或评论的作者可以删除评论
            if (AuthorizationUtil.canModify(loggedInUser, comment.get().getUserId())) {
                commentService.deleteComment(id);  // 删除评论
                return "redirect:/comments";  // 删除后重定向到评论列表页面
            } else {
                return "error/unauthorized";  // 如果没有权限，返回未授权页面
            }
        } else {
            return "redirect:/comments";  // 如果评论不存在，重定向到评论列表页面
        }
    }

    @GetMapping("/search")
    public String searchComments(@RequestParam(required = false) Integer postId,
                                 @RequestParam(required = false) Integer userId,
                                 Model model) {
        List<Comment> comments;
        if (postId != null) {
            comments = commentService.findByPostId(postId);
        } else if (userId != null) {
            comments = commentService.findByUserId(userId);
        } else {
            comments = commentService.findAll();
        }
        model.addAttribute("comments", comments);
        return "comments/list";
    }
}
