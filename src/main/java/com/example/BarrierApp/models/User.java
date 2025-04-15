package com.example.BarrierApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,17}$")
    @Column(unique = true, nullable = false)
    private String phone;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @Builder.Default
    private boolean expired = false;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "users_barriers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "barrier_id")
    )
    private Set<Barrier> barriers = new HashSet<>();
}

