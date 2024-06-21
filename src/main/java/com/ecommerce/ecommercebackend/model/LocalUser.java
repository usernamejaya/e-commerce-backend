package com.ecommerce.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="local_user")
public class LocalUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name ="username" ,unique = true , nullable = false)
    private String username;

    @Column(name="password" ,length = 100 , nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "email" ,unique = true , nullable = false)
    private String email;

    @Column(name = "first_name" ,nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();

    }




