package org.medtech.medmeet.authentication.domain.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "password", length = 30, nullable = false)
    private String password;

}

