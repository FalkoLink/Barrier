package com.example.BarrierApp.controllers;


import com.example.BarrierApp.models.User;
import com.example.BarrierApp.security.UserDetailsImpl;
import com.example.BarrierApp.services.AddressService;
import com.example.BarrierApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final AddressService addressService;
    @GetMapping("/main")
    public String sayHello() {
        return "main";
    }

    @GetMapping("/account")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl personDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userService.findById(personDetails.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("addresses", addressService.findByUsers(new ArrayList<>(Collections.singleton(user))));

        return "profile/account";
    }
}

