package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 根据邮箱查找用户
    Optional<User> findByEmail(String email);

    // 可以根据需要添加更多自定义查询方法
}
