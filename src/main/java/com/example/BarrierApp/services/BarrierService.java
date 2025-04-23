package com.example.BarrierApp.services;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.Barrier;

import java.util.List;

public interface BarrierService {
    List<Barrier> findAll();
    Barrier findById(Long id);
    Barrier save(Barrier barrier);
    List<Barrier> findByAddress(Address address);
    void deleteById(Long id);
}

