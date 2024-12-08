package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findByAuthorId(Integer authorId);
    List<Post> findByCategoryId(Integer categoryId);
    List<Post> searchByTitle(String keyword);
    Optional<Post> findById(Integer postId);
    void savePost(Post post);
    void deletePost(Integer postId);

    List<Post> findAll();

}
