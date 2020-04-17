package com.bootcamp.bootcampecomproject.entities;

import com.bootcamp.bootcampecomproject.utils.CategoryMetadataFieldValuesId;

import javax.persistence.*;

@Entity
public class CategoryMetadataFieldValue{

    @EmbeddedId
    private CategoryMetadataFieldValuesId categoryMetadataFieldValuesId;

    @MapsId("categoryMetadataFieldId")
    @ManyToOne
    private CategoryMetadataField categoryMetadataField;

    @MapsId("categoryId")
    @ManyToOne
    private Category category;

    private String value;

    public CategoryMetadataFieldValuesId getCategoryMetadataFieldValuesId() {
        return categoryMetadataFieldValuesId;
    }

    public void setCategoryMetadataFieldValuesId(CategoryMetadataFieldValuesId categoryMetadataFieldValuesId) {
        this.categoryMetadataFieldValuesId = categoryMetadataFieldValuesId;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
