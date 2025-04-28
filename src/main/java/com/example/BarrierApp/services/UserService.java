package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    List<User> findByAddresses(List<Address> addresses);
    void save(User user);
    void update(Long id, User updatedUser);
    void delete(Long id);

    List<User> search(String search, Address address);
    List<User> search(String search);
}

