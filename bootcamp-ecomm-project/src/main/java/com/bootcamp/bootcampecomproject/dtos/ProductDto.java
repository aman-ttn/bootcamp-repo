package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategoryMetadataDto;
import com.bootcamp.bootcampecomproject.utils.HashMapConverter;

import javax.persistence.Convert;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductDto {

    private String name;
    private String brand;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;
    private Long categoryId;
    private String categoryName;
    private List<ViewCustomerProductVariationDto> productVariationDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public List<ViewCustomerProductVariationDto> getProductVariationDtos() {
        return productVariationDtos;
    }

    public void setProductVariationDtos(List<ViewCustomerProductVariationDto> productVariationDtos) {
        this.productVariationDtos = productVariationDtos;
    }
}
