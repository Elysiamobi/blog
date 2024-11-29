package com.yangcheng.initial.service.impl;

import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.Repository.UserRepository;
import com.yangcheng.initial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        // 这里可以加入密码加密等逻辑
        return userRepository.save(user);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return userRepository.findByUsername(username).isPresent();

    }
}
