package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Post;
import com.yangcheng.initial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // 获取所有文章
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 根据 ID 获取文章
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // 创建文章
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    // 更新文章
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer postId, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        return ResponseEntity.ok(updatedPost);
    }

    // 删除文章
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    // 根据用户 ID 获取该用户所有文章
    @GetMapping("/author/{userId}")
    public List<Post> getPostsByAuthor(@PathVariable Integer userId) {
        return postService.getPostsByAuthor(userId);
    }

    // 根据标题搜索文章
    @GetMapping("/search")
    public List<Post> searchPostsByTitle(@RequestParam String title) {
        return postService.searchPostsByTitle(title);
    }
}