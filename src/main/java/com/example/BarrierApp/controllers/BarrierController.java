package com.example.BarrierApp.controllers;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.Barrier;
import com.example.BarrierApp.services.AddressService;
import com.example.BarrierApp.services.BarrierService;
import com.example.BarrierApp.services.ConnectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/addresses")
@RequiredArgsConstructor
public class BarrierController {

    private final BarrierService barrierService;
    private final AddressService addressService;
    private final ConnectionService connectionService;

//    @GetMapping
//    public String listAddress(Model model) {
//        List<Address> addresses = addressService.findAll();
//        model.addAttribute("addresses", addresses);
//        return "admin/barrier/addresses";
//    }

    @GetMapping("{address_id}/create")
    public String showCreateForm(Model model, @ModelAttribute Barrier barrier, @PathVariable Long address_id) {
        model.addAttribute("address_id", address_id);
        model.addAttribute("connections", connectionService.findAll());
        return "admin/barrier/create_barrier";
    }

//    @GetMapping("/{id}")
//    public String showBarrier(@PathVariable Long id, Model model) {
//        Address address = addressService.findById(id);
//        model.addAttribute("address", address);
//        model.addAttribute("barrier", barrierService.findByAddress(address));
//
//        return "admin/barrier/show_address";
//    }

    @PostMapping("{address_id}/save")
    public String saveBarrier(@Valid @ModelAttribute Barrier barrier,
                              BindingResult bindingResult, @PathVariable Long address_id) {
        if (bindingResult.hasErrors()) {
            return "admin/barrier/create_barrier";
        }
        Address address = addressService.findById(address_id);
        barrier.setAddress(address);
        barrierService.save(barrier);
        return "redirect:/admin/addresses/"+address_id;
    }

    @GetMapping("{address_id}/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, @PathVariable Long address_id) {
        Barrier barrier = barrierService.findById(id);
        model.addAttribute("barrier", barrier);
        model.addAttribute("address_id", address_id);
        model.addAttribute("connections", connectionService.findAll());
        return "admin/barrier/edit_barrier";
    }

    @PostMapping("{address_id}/edit/{id}")
    public String updateBarrier(@Valid @ModelAttribute Barrier barrier,
                              BindingResult bindingResult, @PathVariable Long address_id, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "admin/barrier/create_barrier";
        }
        Address address = addressService.findById(address_id);
        barrier.setAddress(address);
        barrierService.update(id,barrier);
        return "redirect:/admin/addresses/"+address_id;
    }

    @GetMapping("/{address_id}/delete/{id}")
    public String deleteBarrier(@PathVariable Long id, @PathVariable Long address_id) {
        barrierService.deleteById(id);
        return "redirect:/admin/addresses/"+address_id;
    }
}
