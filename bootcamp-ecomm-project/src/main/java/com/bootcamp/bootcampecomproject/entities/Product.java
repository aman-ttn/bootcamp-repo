package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sellerUserId")
    private Seller seller;
    private String name;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;
    private Boolean isDeleted=false;
    private String brand;
    private Boolean isActive=false;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductVariation> productVariationList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductReview> productReviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCancellable() {
        return isCancellable;
    }

    public void setCancellable(Boolean cancellable) {
        isCancellable = cancellable;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductVariation> getProductVariationList() {
        return productVariationList;
    }

    public void setProductVariationList(List<ProductVariation> productVariationList) {
        this.productVariationList = productVariationList;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public Product(Long id, Seller seller, String name, String description, Boolean isCancellable, Boolean isReturnable, Boolean isDeleted, String brand, Boolean isActive, Category category, List<ProductVariation> productVariationList, List<ProductReview> productReviews) {
        this.id = id;
        this.seller = seller;
        this.name = name;
        this.description = description;
        this.isCancellable = isCancellable;
        this.isReturnable = isReturnable;
        this.isDeleted = isDeleted;
        this.brand = brand;
        this.isActive = isActive;
        this.category = category;
        this.productVariationList = productVariationList;
        this.productReviews = productReviews;
    }

    public Product() {
    }
}
