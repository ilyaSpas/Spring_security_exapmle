package com.ilsy.Security.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPagesController {
    @GetMapping("/")
    public String mainPage(Model model) {
//        model.addAttribute();
        return "main";
    }
    @GetMapping("/blog")
    public String blogPage() {
        return "blog";
    }
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }
}
