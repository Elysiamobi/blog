package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // 根据标题查找文章
    List<Post> findByTitleContaining(String title);

    // 根据作者查找文章
    List<Post> findByAuthor_UserId(Integer userId);

    // 根据创建时间排序查找所有文章
    List<Post> findAllByOrderByCreatedAtDesc();


}
