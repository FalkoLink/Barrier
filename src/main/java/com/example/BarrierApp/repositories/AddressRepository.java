package com.example.BarrierApp.repositories;

import com.example.BarrierApp.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

