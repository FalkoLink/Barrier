package com.example.BarrierApp.util;

import com.example.BarrierApp.models.User;
import com.example.BarrierApp.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            if (userDetailsServiceImpl.existsByPhoneNumber(user.getPhone())) {
                errors.rejectValue("phone", "", "Человек с таким номером уже существует");
            }
        }

        try {
            userDetailsServiceImpl.loadUserByUsername(user.getEmail());
            errors.rejectValue("email", "", "Человек с таким email уже существует");
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

    }
}
