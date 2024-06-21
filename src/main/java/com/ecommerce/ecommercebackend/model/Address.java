package com.ecommerce.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "address_line", nullable = false , length = 512)
    private String addressLine;

    @Column(name = "city" , nullable = false)
    private String city;

    @Column(name = "country" , nullable = false , length = 75)
    private String country;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id" , nullable = false)
    private LocalUser user;



}
