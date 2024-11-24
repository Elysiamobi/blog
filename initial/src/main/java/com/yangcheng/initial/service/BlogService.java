package com.yangcheng.initial.service;

import com.yangcheng.initial.Repository.BlogRepository;
import com.yangcheng.initial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    // 发布博客
    @Transactional
    public String publishBlog(String title, String content, Blog.BlogCategory category, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "用户不存在";
        }
        User user = userOptional.get();
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCategory(category);
        blog.setUser(user);
        blogRepository.save(blog);
        return "博客发布成功";
    }

    // 获取所有博客按时间排序
    public List<Blog> getAllBlogsSortedByDate() {
        return blogRepository.findAllByOrderByPublishDateDesc();
    }

    // 按类别获取博客
    public List<Blog> getBlogsByCategory(Blog.BlogCategory category) {
        return blogRepository.findByCategory(category);
    }

    // 按标题查找博客
    public List<Blog> searchBlogsByTitle(String title) {
        return blogRepository.findByTitleContaining(title);
    }

    // 获取博客详情
    public Optional<Blog> getBlogById(Integer blogId) {
        return blogRepository.findById(blogId);
    }

    // 更新博客内容
    @Transactional
    public String updateBlog(Integer blogId, String title, String content) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setTitle(title);
            blog.setContent(content);
            blogRepository.save(blog);
            return "博客更新成功";
        }
        return "博客不存在";
    }

    // 删除博客
    @Transactional
    public String deleteBlog(Integer blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            blogRepository.deleteById(blogId);
            return "博客删除成功";
        }
        return "博客不存在";
    }
}
