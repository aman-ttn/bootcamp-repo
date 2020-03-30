package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductReview {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="customerUserId")
    private Customer customer;
    private String review;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
}
