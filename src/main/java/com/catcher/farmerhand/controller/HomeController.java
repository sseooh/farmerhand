package com.catcher.farmerhand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // home.html을 렌더링
    }

//    @GetMapping("/program-board")
//    public String board() {
//        return "program-board"; // board.html을 렌더링
//    }
//
//    @GetMapping("/policy-board")
//    public String policyBoard() {
//        return "policy-board"; // board.html을 렌더링
//    }
}