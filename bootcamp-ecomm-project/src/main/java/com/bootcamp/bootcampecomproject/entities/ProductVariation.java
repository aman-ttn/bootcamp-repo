package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;

@Entity
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private Integer quantityAvailable;
    private Double price;
    private String metadata;
    private String primaryImageName;
}
