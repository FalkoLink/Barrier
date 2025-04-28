package com.example.BarrierApp.repositories;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.Barrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarrierRepository extends JpaRepository<Barrier, Long> {
    List<Barrier> findByAddress(Address address);
}

