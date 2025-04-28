package com.example.BarrierApp.repositories;

import com.example.BarrierApp.models.Address;
import com.example.BarrierApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByPhone(String phoneNumber);
    List<User> findByAddresses(List<Address> addresses);
    Optional<User> findById(Long id);
    void deleteById(Long id);

    @Query("""
    SELECT u FROM User u
    WHERE (
        LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
    )
    AND :address NOT MEMBER OF u.addresses
""")
    List<User> searchNotInAddress(@Param("search") String search, @Param("address") Address address);

    @Query("""
    SELECT u FROM User u
    WHERE (
        LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""")
    List<User> search(@Param("search") String search);

}

