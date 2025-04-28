package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.User;
import com.example.BarrierApp.repositories.UserRepository;
import com.example.BarrierApp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(s);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsImpl(user.get());
    }

    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhone(phoneNumber);
    }

}
