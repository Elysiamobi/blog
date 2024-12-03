package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Post;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Integer postId);
    Post createPost(Post post);
    Post createPostWithCategory(Post post, Integer categoryId); // 新增
    Post updatePost(Integer postId, Post post);
    void deletePost(Integer postId);
    List<Post> getPostsByAuthor(Integer userId);
    List<Post> searchPostsByTitle(String title);
}
