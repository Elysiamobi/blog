package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Category;
import com.yangcheng.initial.entity.User;
import com.yangcheng.initial.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list"; // Render categories/list.html
    }

    @GetMapping("/new")
    public String showAddCategoryForm(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.isAdmin()) {
            return "error/unauthorized"; // 如果用户不是管理员，返回未授权页面
        }
        model.addAttribute("category", new Category()); // Pass a new Category object
        return "categories/form"; // Render form.html
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Integer id,HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.isAdmin()) {
            return "error/unauthorized"; // 如果用户不是管理员，返回未授权页面
        }
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categories/form"; // Render categories/form.html for editing
        } else {
            return "redirect:/categories"; // Redirect to list if category not found
        }
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category,HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.isAdmin()) {
            return "error/unauthorized"; // 如果用户不是管理员，返回未授权页面
        }
        categoryService.saveCategory(category);
        return "redirect:/categories"; // Redirect to categories list after saving
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id,HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.isAdmin()) {
            return "error/unauthorized"; // 如果用户不是管理员，返回未授权页面
        }
        categoryService.deleteCategory(id);
        return "redirect:/categories"; // Redirect to categories list after deletion
    }
}