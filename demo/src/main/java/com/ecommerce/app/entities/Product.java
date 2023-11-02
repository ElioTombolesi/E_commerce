package com.ecommerce.app.entities;


import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    private String name;

    private double price;



}
