package com.bootcamp.bootcampecomproject.dtos;

import java.util.Set;

public class CategoryMetadataFieldValuesDto {
    private Long categoryId;
    private Long fieldId;
    private Set<String> fieldValues;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Set<String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public CategoryMetadataFieldValuesDto(Long categoryId, Long fieldId, Set<String> fieldValues) {
        this.categoryId = categoryId;
        this.fieldId = fieldId;
        this.fieldValues = fieldValues;
    }

    @Override
    public String toString() {
        return "CategoryMetadataFieldValuesDto{" +
                "categoryId=" + categoryId +
                ", fieldId=" + fieldId +
                ", fieldValues=" + fieldValues +
                '}';
    }
}
