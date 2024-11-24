package com.yangcheng.initial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 根据用户名查询用户
    Optional<User> findByUsername(String username);

    // 检查用户名是否存在
    boolean existsByUsername(String username);

    // 用户登录时验证用户名和密码
    Optional<User> findByUsernameAndPassword(String username, String password);
}
