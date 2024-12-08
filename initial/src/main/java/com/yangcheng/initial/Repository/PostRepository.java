package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Find posts by author ID
    List<Post> findByAuthorId(Integer authorId);

    // Find posts by category ID
    List<Post> findByCategoryId(Integer categoryId);

    // Search posts by title containing a specific keyword
    List<Post> findByTitleContaining(String keyword);

    @Query("SELECT p, c.name as categoryName, u.username as authorName " +
            "FROM Post p " +
            "LEFT JOIN Category c ON p.categoryId = c.categoryId " +
            "LEFT JOIN User u ON p.authorId = u.userId")
    List<Object[]> findAllWithCategoryAndAuthor();
}
