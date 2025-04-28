package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.Barrier;
import com.example.BarrierApp.repositories.BarrierRepository;
import com.example.BarrierApp.services.BarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarrierServiceImpl implements BarrierService {

    private final BarrierRepository barrierRepository;

    @Override
    public List<Barrier> findAll() {
        return barrierRepository.findAll();
    }

    @Override
    public Barrier findById(Long id) {
        return barrierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Barrier not found with id: " + id));
    }

    @Override
    @Transactional
    public Barrier save(Barrier barrier) {
        return barrierRepository.save(barrier);
    }

    @Override
    public List<Barrier> findByAddress(Address address) {
        return barrierRepository.findByAddress(address);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        barrierRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Barrier update(Long id, Barrier barrier) {
        barrier.setId(id);
        return barrierRepository.save(barrier);
    }
}