package com.example.BarrierApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Size(max = 100, message = "Имя не может быть длиннее 100 символов")
    private String firstName;

    @NotBlank
    @Size(max = 100, message = "Фамилия не может быть длиннее 100 символов")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,17}$", message = "Неверный формат номера телефона")
    @Column(unique = true, nullable = false)
    private String phone;

    @Email(message = "Неверный формат электронной почты")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 100, message = "Пароль должен быть длиной от 8 до 100 символов")
    private String password;

    @Builder.Default
    private boolean expired = false;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "users")
    private Set<Address> addresses = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

