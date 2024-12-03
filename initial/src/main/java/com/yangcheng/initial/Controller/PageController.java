package com.yangcheng.initial.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/login.html")
    public String loginPage() {
        return "login"; // 对应的是 templates/login.html
    }

    @GetMapping("/register.html")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/detail.html")
    public String detailPage(){
        return "detail";
    }

    @GetMapping("/index.html")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/create.html")
    public String createPage(){
        return "create";
    }
    @GetMapping("/update.html")
    public String updatePage(){
        return "update";
    }

}