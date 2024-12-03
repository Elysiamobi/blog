package com.yangcheng.initial.service.impl;

import com.yangcheng.initial.Repository.UserRepository;
import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
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

    /**
     * 检查用户是否存在
     *
     * @param username 用户名
     * @return 如果用户存在返回true，否则返回false
     */
    @Override
    public boolean checkIfUserExists(String username) {
        // 使用用户仓储根据用户名查询用户，如果查询结果存在则返回true，否则返回false
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Integer userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null) {
            existingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
