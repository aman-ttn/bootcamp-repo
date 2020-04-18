package com.bootcamp.bootcampecomproject.dtos;

import java.util.List;

public class OneCategoryDto {
    private Long id;
    private String categoryName;
    private List<String> parentCategories;
    private String childCategory;

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<String> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public String getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(String childCategory) {
        this.childCategory = childCategory;
    }

    public OneCategoryDto() {
    }
}
