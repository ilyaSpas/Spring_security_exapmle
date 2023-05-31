package com.ilsy.Security.Controllers;

import com.ilsy.Security.Models.Post;
import com.ilsy.Security.Repo.PostRepository;
import com.ilsy.Security.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainPagesController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/")
    public String mainPage(Model model) {
//        model.addAttribute();
        return "main";
    }
    @GetMapping("/blog")
    public String blogPage(Model model, Principal principal) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("user", principal);
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
