package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "success", required = false) Boolean success, Model model) {
        if (success != null && success) {
            model.addAttribute("success", "Registration successful! Please log in.");
        }
        return "login"; // Render login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(username));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) { // Ideally, hash and compare passwords
                session.setAttribute("loggedInUser", user);
                return "redirect:/"; // Redirect to homepage
            } else {
                model.addAttribute("error", "Invalid password.");
                return "login";
            }
        } else {
            model.addAttribute("error", "User not found.");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear the session
        return "redirect:/login"; // Redirect to login page
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register"; // Render register.html
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String confirmPassword,
                                      Model model) {
        // Check if the username already exists
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        // Save the new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Ideally, passwords should be hashed before saving
        userService.saveUser(newUser);
        newUser.setRoleId(1); // Default to "USER"
        userService.saveUser(newUser); // Save the user (timestamps handled automatically)
        model.addAttribute("success", "successful");
        // Redirect to login page with a success message
        return "redirect:/login?success=true";
    }
}
