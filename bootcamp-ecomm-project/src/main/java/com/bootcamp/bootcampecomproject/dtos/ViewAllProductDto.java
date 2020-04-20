package com.bootcamp.bootcampecomproject.dtos;

import java.util.List;

public class ViewAllProductDto {
    private Long productId;
    private String productName;
    private Long categoryId;
    private String categoryName;
    private List<CustomerVariationDto> variations;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CustomerVariationDto> getVariations() {
        return variations;
    }

    public void setVariations(List<CustomerVariationDto> variations) {
        this.variations = variations;
    }
}
