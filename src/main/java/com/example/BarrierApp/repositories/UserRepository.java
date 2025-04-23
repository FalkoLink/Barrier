package com.example.BarrierApp.repositories;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByPhone(String phoneNumber);
    Optional<User> findById(Long id);

    List<User> findAllByAddresses(Address address);
    void deleteById(Long id);
}

