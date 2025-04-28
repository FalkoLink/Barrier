package com.example.BarrierApp.controllers;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import com.example.BarrierApp.services.AddressService;
import com.example.BarrierApp.services.BarrierService;
import com.example.BarrierApp.services.UserService;
import com.example.BarrierApp.util.AddressValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final BarrierService barrierService;
    private final AddressService addressService;
    private final UserService userService;
    private final AddressValidator addressValidator;

    @GetMapping
    public String listAddress(Model model) {
        List<Address> addresses = addressService.findAll();
        model.addAttribute("addresses", addresses);
        return "admin/address/addresses";
    }

    @GetMapping("/{id}")
    public String showAddress(@RequestParam(required = false) String search, @PathVariable Long id, Model model) {
        Address address = addressService.findById(id);
        if (search != null && !search.isEmpty()) {
            List<User> searchedUsers = userService.search(search, address);
            model.addAttribute("searchedUsers", searchedUsers);
            System.out.println(searchedUsers);
            model.addAttribute("search", search);
        }
        model.addAttribute("address", address);
        model.addAttribute("barriers", barrierService.findByAddress(address));
        model.addAttribute("users", userService.findByAddresses(new ArrayList<>(Collections.singletonList(address))));

        return "admin/address/show_address";
    }

    @PostMapping("/{address_id}/add-user/{user_Id}")
    public String addUserToAddress(@PathVariable Long user_Id, @PathVariable Long address_id) {
        User user = userService.findById(user_Id).orElseThrow(() -> new IllegalArgumentException("User not found"));;
        Address address = addressService.findById(address_id);

        if (!user.getAddresses().contains(address)) {
            user.getAddresses().add(address);
        }
        if (!address.getUsers().contains(user)) {
            address.getUsers().add(user);
        }

        userService.save(user);
        addressService.save(address);

        return "redirect:/admin/addresses/" + address_id;
    }

    @PostMapping("/{address_id}/remove-user/{user_Id}")
    public String removeUserFromAddress(@PathVariable Long user_Id, @PathVariable Long address_id) {
        User user = userService.findById(user_Id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Address address = addressService.findById(address_id);

        user.getAddresses().remove(address);
        address.getUsers().remove(user);

        userService.save(user);
        addressService.save(address);

        return "redirect:/admin/addresses/" + address_id;
    }

    @GetMapping("/create")
    public String showCreateForm(@ModelAttribute("address") Address address) {
        return "admin/address/create_address";
    }

    @PostMapping("/save")
    public String saveAddress(@ModelAttribute("address") @Valid Address address,
                              BindingResult bindingResult) {
        addressValidator.validate(address, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/address/create_address";
        }
        Long id = addressService.save(address).getId();
        return "redirect:/admin/addresses/"+id;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Address address = addressService.findById(id);
        model.addAttribute("address", address);
        return "admin/address/edit_address";
    }

    @PostMapping("/{id}")
    public String updateAddress(@PathVariable Long id, @ModelAttribute("address") @Valid Address address,
                                BindingResult bindingResult) {
        addressValidator.validate(address, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/address/edit_address";
        }
        addressService.update(id,address);
        return "redirect:/admin/addresses/"+id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBarrier(@PathVariable Long id) {
        addressService.deleteById(id);
        return "redirect:/admin/addresses";
    }
}
