package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册用户
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.checkIfUserExists(user.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // 根据用户名查找用户
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根据邮箱查找用户
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // 登录成功后将用户信息保存在 Session 中
            session.setAttribute("username", username);
            session.setAttribute("role_id", user.get().getRoleId()); // 保存角色信息
//            session.setAttribute("role_name", user.get().getRoleName());
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    // 获取当前登录用户的信息
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("username", username);
            userInfo.put("role_id", session.getAttribute("role_id"));
            return ResponseEntity.ok(userInfo);
        }
        return ResponseEntity.status(401).body(Map.of("error", "No user logged in"));
    }


    // 检查管理员权限
    @GetMapping("/check-admin")
    public ResponseEntity<String> checkAdmin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Integer roleId = (Integer) session.getAttribute("role_id");
//        String roleName = (String) session.getAttribute("role_name");

        if ("admin".equals(username) && roleId == 2 ) {
            return ResponseEntity.ok("Admin access granted");
        }
        return ResponseEntity.status(403).body("Forbidden: Not an admin");
    }


    // 用户登出
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();  // 清除当前会话
        return ResponseEntity.ok("Logout successful");
    }
}
