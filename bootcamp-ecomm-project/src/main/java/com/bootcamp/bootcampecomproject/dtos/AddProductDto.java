package com.bootcamp.bootcampecomproject.dtos;

import javax.validation.constraints.NotNull;

public class AddProductDto {
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private Long categoryId;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;

    public AddProductDto(String name, String brand, Long categoryId, String description, Boolean isCancellable, Boolean isReturnable) {
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.description = description;
        this.isCancellable = isCancellable;
        this.isReturnable = isReturnable;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}
