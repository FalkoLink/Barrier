package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.User;
import com.example.BarrierApp.repositories.UserRepository;
import com.example.BarrierApp.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(Long id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
