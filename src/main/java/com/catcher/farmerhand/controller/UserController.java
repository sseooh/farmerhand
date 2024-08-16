package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.Users;
import com.catcher.farmerhand.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", new Users()); // Users 객체를 모델에 추가
        return "login";
    }

    @PostMapping("/login")
    public String login(Users user, Model model) {
        Boolean confirm = userService.login(user.getEmail(), user.getPassword());

        if (confirm) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password"); // 에러 메시지 추가
            return "login"; // 다시 로그인 페이지로 이동
        }
    }
}
