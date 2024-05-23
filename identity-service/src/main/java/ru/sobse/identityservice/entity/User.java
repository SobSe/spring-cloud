package ru.sobse.identityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    private Long id;

    @Column
    @Setter
    @Getter
    private String email;

    @Column
    @Setter
    @Getter
    private String password;

}
