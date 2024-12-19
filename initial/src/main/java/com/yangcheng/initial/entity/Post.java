package com.yangcheng.initial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    @Lob
    private String content;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Eagerly load the associated Author (User) entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;

    // Eagerly load the associated Category entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Transient
    private String categoryName; // Category name for display

    @Transient
    private String authorName; // Author name for display

    // Getter and Setter for categoryName
    // Getter for categoryName (based on the loaded category)
    public String getCategoryName() {
        return category != null ? category.getName() : null;
    }

    // Getter for authorName (based on the loaded author)
    public String getAuthorName() {
        return author != null ? author.getUsername() : null;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}