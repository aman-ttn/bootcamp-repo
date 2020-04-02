package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;

@Entity
public class ProductReview {
    @Id
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name="customerUserId")
    private Orders orders;
    private String review;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
}
