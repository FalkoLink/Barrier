package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
    Optional<Role> findById(Long id);

    List<Role> findAll();
}
