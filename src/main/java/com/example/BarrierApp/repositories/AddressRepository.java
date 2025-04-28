package com.example.BarrierApp.repositories;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByAddressName(String addressName);
    List<Address> findByUsers(List<User> addresses);
}

