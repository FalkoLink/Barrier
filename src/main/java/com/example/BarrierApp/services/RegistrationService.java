package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Role;
import com.example.BarrierApp.models.User;
import com.example.BarrierApp.repositories.RoleRepository;
import com.example.BarrierApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void register(User user, BindingResult bindingResult) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRole(userRole);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("users_phone_key")) {
                bindingResult.rejectValue("phone", "error.user", "Номер телефона уже используется.");
            } else if (e.getMessage().contains("users_email_key")) {
                bindingResult.rejectValue("email", "error.user", "Email уже используется.");
            } else {
                bindingResult.reject("error.user", "Произошла ошибка при регистрации.");
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}

