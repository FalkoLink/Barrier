package com.example.BarrierApp.util;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.services.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {

    private final AddressServiceImpl addressServiceImpl;

    @Autowired
    public AddressValidator(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Address.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Address address = (Address) o;

        if (addressServiceImpl.existsByAddressName(address.getAddressName())) {
            errors.rejectValue("addressName", "", "Такой адрес уже существует");
        }
        return;
    }
}
