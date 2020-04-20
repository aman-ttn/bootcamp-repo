package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.dtos.categorySeller.CategoryMetadataDto;
import com.bootcamp.bootcampecomproject.entities.Category;

import java.util.List;
import java.util.Set;

public class CategoryFilteringDto {
    private String categoryName;
    private List<CategoryMetadataDto> categoryMetadataDto;
    private Set<String> brandList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryMetadataDto> getCategoryMetadataDto() {
        return categoryMetadataDto;
    }

    public void setCategoryMetadataDto(List<CategoryMetadataDto> categoryMetadataDto) {
        this.categoryMetadataDto = categoryMetadataDto;
    }

    public Set<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(Set<String> brandList) {
        this.brandList = brandList;
    }

}
