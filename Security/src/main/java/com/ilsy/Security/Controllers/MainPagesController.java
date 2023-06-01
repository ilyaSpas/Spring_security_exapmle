package com.ilsy.Security.Controllers;

import com.ilsy.Security.Models.Post;
import com.ilsy.Security.Repo.PostRepository;
import com.ilsy.Security.Repo.UserRepository;
import com.ilsy.Security.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainPagesController {
    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String mainPage(Model model, Principal principal) {
        if(principal != null) {
            model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        }
        return "main";
    }
    @GetMapping("/blog")
    public String blogPage(Model model, Principal principal) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("user", principal);
        if(principal != null) {
            model.addAttribute("userAuth", userRepository.findByEmail(principal.getName()));
        }
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> news = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional <Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogEdit"; //
    }

    @PostMapping("/blog/{id}/edit")
    public String blogAddPostUpdate(@PathVariable(value = "id") long id,
                                    @RequestParam String title,
                                    @RequestParam String anons,
                                    @RequestParam String text) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setText(text);
        postRepository.save(post);
        return "redirect:/blog/{id}";
    }






    @PostMapping("/blog/{id}/remote")
    public String blogAddPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

    @GetMapping("/about")
    public String aboutPage(Model model, Principal principal) {
        if(principal != null) {
            model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        }
        return "about";
    }
    @GetMapping("/contacts")
    public String contactsPage(Model model, Principal principal) {
        if(principal != null) {
            model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        }
        return "contacts";
    }
}
