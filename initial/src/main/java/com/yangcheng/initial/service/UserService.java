package com.yangcheng.initial.service;

import com.yangcheng.initial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 注册用户
    @Transactional
    public String registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "用户名已存在";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(User.UserType.注册用户);
        userRepository.save(user);
        return "注册成功";
    }

    // 用户登录
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // 获取用户信息
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    // 更新用户信息（如修改密码等）
    @Transactional
    public String updateUser(Integer userId, String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
            return "用户信息更新成功";
        }
        return "用户不存在";
    }

    // 删除用户
    @Transactional
    public String deleteUser(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return "用户删除成功";
        }
        return "用户不存在";
    }
}
