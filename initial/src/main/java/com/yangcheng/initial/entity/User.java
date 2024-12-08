package com.yangcheng.initial.entity;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String password;

    private String email;

    @Column(name = "role_id")
    private Integer roleId;// 1 = USER, 2 = ADMIN

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean isAdmin() {
        return this.roleId != null && this.roleId == 2;
    }

    public boolean isUser() {
        return this.roleId != null && this.roleId == 1;
    }
}