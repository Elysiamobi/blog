package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Comment;
import com.yangcheng.initial.entity.Post;
import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.CategoryService;
import com.yangcheng.initial.service.PostService;
import com.yangcheng.initial.service.CommentService;
import com.yangcheng.initial.service.UserService;
import com.yangcheng.initial.utils.AuthorizationUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list"; // Render posts/list.html

    }

    @GetMapping("/")
    public String showHomepage(Model model) {
        // Fetch all posts from the database
        List<Post> posts = postService.findAll();

        // Add posts to the model
        model.addAttribute("posts", posts);
//        for(Post post: posts){
//            post.setAuthorName(userService.findById(post.getAuthorId()).orElse(new User()).getUsername());
//            post.setCategoryName(categoryService.findById(post.getCategoryId()).orElseThrow(()->new RuntimeException("category Not found")).getName());
//        }
        return "index"; // Render the index.html template
    }
    @GetMapping("/new")
    public String showAddPostForm(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.findAll());
        return "posts/form";
    }


    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Integer id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Post> post = postService.findById(id);

        if (post.isPresent() && AuthorizationUtil.canModify(loggedInUser, post.get().getAuthorId())) {
            model.addAttribute("post", post.get());

            return "posts/form";
        } else {
            return "error/unauthorized"; // Render an unauthorized error page
        }
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        post.setAuthorId(loggedInUser.getUserId());

        postService.savePost(post);
        return "redirect:/posts"; // Redirect to posts list after saving
    }
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Post> post = postService.findById(id);

        if (post.isPresent() && AuthorizationUtil.canModify(loggedInUser, post.get().getAuthorId())) {
            postService.deletePost(id);
            return "redirect:/posts";
        } else {
            return "error/unauthorized"; // Render an unauthorized error page
        }
    }

    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable Integer id, HttpSession session,Model model) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());

            User loggedInUser = (User) session.getAttribute("loggedInUser");
            Integer userId = loggedInUser != null ? loggedInUser.getUserId() : null;

            // Fetch comments for the post
            List<Comment> comments = commentService.findByPostId(id);
            model.addAttribute("comments", comments);
            model.addAttribute("userId", userId);
            return "posts/view"; // Render posts/view.html
        } else {
            model.addAttribute("error", "Post not found.");
            return "redirect:/posts";
        }
    }
}