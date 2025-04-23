package com.example.BarrierApp.controllers;


import com.example.BarrierApp.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String sayHello() {
        return "main";
    }

//    @GetMapping("/showUserInfo")
//    public String showUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl personDetails = (UserDetailsImpl) authentication.getPrincipal();
//        System.out.println(personDetails.getUser());
//
//        return "main";
//    }

    @GetMapping("/account")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl personDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", personDetails.getUser());
        return "profile/account";
    }
}

