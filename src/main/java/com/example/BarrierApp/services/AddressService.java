package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address findById(Long id);
    Address save(Address address);
    void deleteById(Long id);
}