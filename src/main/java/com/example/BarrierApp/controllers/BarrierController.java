package com.example.BarrierApp.controllers;

import com.example.BarrierApp.models.Barrier;
import com.example.BarrierApp.services.AddressService;
import com.example.BarrierApp.services.BarrierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/barriers")
@RequiredArgsConstructor
public class BarrierController {

    private final BarrierService barrierService;
    private final AddressService addressService;

//    @GetMapping
//    public String listAddress(Model model) {
//        List<Address> addresses = addressService.findAll();
//        model.addAttribute("addresses", addresses);
//        return "admin/barrier/addresses";
//    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("barrier", new Barrier());
        model.addAttribute("addresses", addressService.findAll());
        return "barrier/form";
    }

//    @GetMapping("/{id}")
//    public String showAddress(@PathVariable Long id, Model model) {
//        Address address = addressService.findById(id);
//        model.addAttribute("address", address);
//        model.addAttribute("barrier", barrierService.findByAddress(address));
//
//        return "admin/barrier/show_address";
//    }

    @PostMapping("/save")
    public String saveBarrier(@Valid @ModelAttribute Barrier barrier,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addresses", addressService.findAll());
            return "barrier/form";
        }
        barrierService.save(barrier);
        return "redirect:/admin/barriers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Barrier barrier = barrierService.findById(id);
        model.addAttribute("barrier", barrier);
        model.addAttribute("addresses", addressService.findAll());
        return "barrier/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBarrier(@PathVariable Long id) {
        barrierService.deleteById(id);
        return "redirect:/admin/barriers";
    }
}
