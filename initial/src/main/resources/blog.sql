-- 创建数据库
CREATE DATABASE yc_blog;
USE yc_blog;

-- 1. 用户表: 存储用户信息
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,  -- 用户ID
                       username VARCHAR(100) NOT NULL,          -- 用户名
                       password VARCHAR(255) NOT NULL,          -- 密码
                       email VARCHAR(100),                      -- 邮箱
                       role_id INT,                             -- 用户角色ID（外键）
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                       INDEX idx_username(username)
);

-- 2. 角色表: 存储用户角色信息
CREATE TABLE roles (
                       role_id INT AUTO_INCREMENT PRIMARY KEY,  -- 角色ID
                       role_name VARCHAR(50) NOT NULL           -- 角色名称（比如 "USER", "ADMIN"）
);

-- 插入角色数据
INSERT INTO roles (role_name) VALUES
                                  ('USER'),
                                  ('ADMIN');

-- 3. 文章表: 存储博客文章信息
CREATE TABLE posts (
                       post_id INT AUTO_INCREMENT PRIMARY KEY,    -- 文章ID
                       title VARCHAR(255) NOT NULL,                -- 文章标题
                       content TEXT NOT NULL,                      -- 文章内容
                       author_id INT,                              -- 作者ID（外键，指向 users 表）
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                       INDEX idx_title(title)
);

-- 4. 评论表: 存储文章的评论
CREATE TABLE comments (
                          comment_id INT AUTO_INCREMENT PRIMARY KEY,  -- 评论ID
                          post_id INT,                                -- 文章ID（外键，指向 posts 表）
                          user_id INT,                                -- 用户ID（外键，指向 users 表）
                          content TEXT NOT NULL,                      -- 评论内容
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                          INDEX idx_post_id(post_id),
                          FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

INSERT INTO users (username, password,role_id)
VALUES ('yc',123,1);