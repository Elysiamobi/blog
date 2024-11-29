package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 根据文章查找所有评论
    List<Comment> findByPost_PostId(Integer postId);

    // 根据用户查找所有评论
    List<Comment> findByUser_UserId(Integer userId);


}
