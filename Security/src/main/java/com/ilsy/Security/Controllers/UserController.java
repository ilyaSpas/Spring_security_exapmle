package com.ilsy.Security.Controllers;

import com.ilsy.Security.Models.User;
import com.ilsy.Security.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "registration";
        }
        else {
            userService.createUser(user);
//        if (!userService.createUser(user)){
//            model.addAttribute("errorMessage", "Пользователь с такой почтой уже существует.");
//            return "registration";
//        }
        }
        return "redirect:/login";
    }
}
