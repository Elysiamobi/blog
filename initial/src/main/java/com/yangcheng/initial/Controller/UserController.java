package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.UserService;
import com.yangcheng.initial.utils.AuthorizationUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list"; // Render users/list.html
    }

    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form"; // Render users/form.html for creating a user
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<User> user = userService.findById(id);

        if (user.isPresent() && AuthorizationUtil.canModify(loggedInUser, user.get().getUserId())) {
            model.addAttribute("user", user.get());
            return "users/form";
        } else {
            return "error/unauthorized"; // Render an unauthorized error page
        }
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Allow only admins or the user themselves to edit their information
        if (loggedInUser.isAdmin() || (loggedInUser.isUser()&&loggedInUser.getUserId().equals(user.getUserId()))) {
            userService.saveUser(user); // `updatedAt` is automatically updated
            return "redirect:/users";
        } else {
            return "error/unauthorized"; // Render unauthorized error page
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id,HttpSession session) {
        User loggedInUser=(User)session.getAttribute("loggedInUser");
        if(loggedInUser!=null&&loggedInUser.isAdmin()){
            userService.deleteUser(id);
            return "redirect:/users"; // Redirect to users list after deletion
        }else{
            return "error/unauthorized";
        }

    }
}