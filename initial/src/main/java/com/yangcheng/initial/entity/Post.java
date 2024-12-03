package com.yangcheng.initial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;  // 文章标题
    private String content;  // 文章内容

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;  // 文章的作者

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;  // 文章的评论

    @JoinColumn(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
