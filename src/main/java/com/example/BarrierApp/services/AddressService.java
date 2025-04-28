package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    Address findById(Long id);

    Address save(Address address);

    Address update(Long id, Address address);

    boolean existsByAddressName(String addressName);

    List<Address> findByUsers(List<User> users);

    void deleteById(Long id);
}