package com.ecommerce.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name ="name" , nullable = false , unique = true)
    private String name;

    @Column(name ="short_description" , nullable = false )
    private String shortDescription;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "price" , nullable = false)
    private double price;

    @OneToOne(mappedBy = "product",cascade = CascadeType.REMOVE ,optional = false ,orphanRemoval = true)
    private Inventory inventory;
}
