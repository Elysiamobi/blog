package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostId(Integer postId);

    // Find comments by user ID
    List<Comment> findByUserId(Integer userId);


}
