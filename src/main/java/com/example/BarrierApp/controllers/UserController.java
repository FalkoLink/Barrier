package com.example.BarrierApp.controllers;

import com.example.BarrierApp.models.User;
import com.example.BarrierApp.services.AdminService;
import com.example.BarrierApp.services.RoleService;
import com.example.BarrierApp.util.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final AdminService adminService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(AdminService adminService, RoleService roleService, UserValidator userValidator) {
        this.adminService = adminService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String listUsers(Model model,  HttpServletRequest request) {
        model.addAttribute("users", adminService.findAll());
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin/user/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", adminService.findById(id).orElseThrow());
        model.addAttribute("roles", roleService.findAll());
        return "admin/user/edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/user/edit_user";
        }
        adminService.update(id, user);

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/admin/users";
    }
}
