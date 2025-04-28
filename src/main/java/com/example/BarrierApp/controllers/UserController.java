package com.example.BarrierApp.controllers;

import com.example.BarrierApp.models.Role;
import com.example.BarrierApp.models.User;
import com.example.BarrierApp.services.AddressService;
import com.example.BarrierApp.services.RoleService;
import com.example.BarrierApp.services.UserService;
import com.example.BarrierApp.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;
    private final AddressService addressService;

    @GetMapping
    public String listUsers(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            List<User> searchedUsers = userService.search(search);
            model.addAttribute("users", searchedUsers);
            model.addAttribute("search", search);
        }else{
            model.addAttribute("users", userService.findAll());
        }
        return "admin/user/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRole());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("addresses", addressService.findByUsers(new ArrayList<>(Collections.singleton(user))));

        return "admin/user/edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam("roleId") Long roleId) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = roleService.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.setRole(role);
        userService.update(id, user);

        return "redirect:/admin/users/edit/"+id;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
