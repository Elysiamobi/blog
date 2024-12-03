package com.yangcheng.initial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private Integer roleId;

    private String username;
    private String password;
    private String email;

//    @ManyToOne
@JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;  // 用户角色

    @OneToMany(mappedBy = "author")
    private List<Post> posts;  // 用户发布的文章

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;  // 用户的评论
}
