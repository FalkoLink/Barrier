package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import com.example.BarrierApp.repositories.AddressRepository;
import com.example.BarrierApp.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + id));
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(Long id, Address address) {
        address.setId(id);
        return addressRepository.save(address);
    }

    @Override
    public boolean existsByAddressName(String addressName) {
        return addressRepository.existsByAddressName(addressName);
    }

    @Override
    public List<Address> findByUsers(List<User> users) {
        return addressRepository.findByUsers(users);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
