package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import com.example.BarrierApp.repositories.UserRepository;
import com.example.BarrierApp.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByAddresses(List<Address> addresses) {
        return userRepository.findByAddresses(addresses);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> search(String search, Address address) {
        return userRepository.searchNotInAddress(search, address);
    }

    @Override
    public List<User> search(String search) {
        return userRepository.search(search);
    }
}
