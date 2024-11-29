package com.yangcheng.initial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;  // 评论所属的文章

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 评论的用户

    private String content;  // 评论内容
}
