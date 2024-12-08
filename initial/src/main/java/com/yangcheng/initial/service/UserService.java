package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);
    Optional<User> findById(Integer userId);
    void saveUser(User user);
    void deleteUser(Integer userId);

    List<User> findAll();
}
