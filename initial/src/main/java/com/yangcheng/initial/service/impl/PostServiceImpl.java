package com.yangcheng.initial.service.impl;


import com.yangcheng.initial.Repository.PostRepository;
import com.yangcheng.initial.entity.Post;
import com.yangcheng.initial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Post getPostById(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Integer postId, Post post) {
        // 获取文章并更新内容
        Post existingPost = getPostById(postId);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getPostsByAuthor(Integer userId) {
        return postRepository.findByAuthor_UserId(userId);
    }

    @Override
    public List<Post> searchPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
