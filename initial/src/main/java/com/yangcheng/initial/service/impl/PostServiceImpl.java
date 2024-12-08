package com.yangcheng.initial.service.impl;


import com.yangcheng.initial.Repository.*;
import com.yangcheng.initial.entity.Post;
import com.yangcheng.initial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findByAuthorId(Integer authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Post> findByCategoryId(Integer categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Post> searchByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    @Override
    public Optional<Post> findById(Integer postId) {
        return postRepository.findById(postId);
    }

    @Override
    public void savePost(Post post) {
         postRepository.save(post);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
