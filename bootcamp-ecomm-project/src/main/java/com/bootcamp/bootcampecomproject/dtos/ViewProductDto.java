package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategoryMetadataDto;
import com.bootcamp.bootcampecomproject.entities.CategoryMetadataField;
import com.bootcamp.bootcampecomproject.entities.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ViewProductDto {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;
    private Boolean isActive;
    private Long categoryId;
    private String categoryName;
    private List<CategoryMetadataDto> categoryMetadataDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public List<CategoryMetadataDto> getCategoryMetadataDtoList() {
        return categoryMetadataDtoList;
    }

    public void setCategoryMetadataDtoList(List<CategoryMetadataDto> categoryMetadataDtoList) {
        this.categoryMetadataDtoList = categoryMetadataDtoList;
    }
}
