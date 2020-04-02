package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    @OneToOne(mappedBy = "orderProduct")
    private OrderStatus orderStatus;
    private Integer quantity;
    private Double price;

    private String productVariationMetadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductVariationMetadata() {
        return productVariationMetadata;
    }

    public void setProductVariationMetadata(String productVariationMetadata) {
        this.productVariationMetadata = productVariationMetadata;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orders=" + orders +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productVariationMetadata='" + productVariationMetadata + '\'' +
                '}';
    }
}
