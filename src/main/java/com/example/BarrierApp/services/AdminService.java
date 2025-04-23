package com.example.BarrierApp.services;

import com.example.BarrierApp.models.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<User> findAll();
    Optional<User> findById(Long id);
    void save(User user);
    void update(Long id, User updatedUser);
    void delete(Long id);
}

