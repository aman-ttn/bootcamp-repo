package com.bootcamp.bootcampecomproject.dtos.categorySeller;

import java.util.List;

public class CategoryMetadataDto {
    private String metadataField;
    private String metadataFieldValues;

    public String getMetadataField() {
        return metadataField;
    }

    public void setMetadataField(String metadataField) {
        this.metadataField = metadataField;
    }

    public String getMetadataFieldValues() {
        return metadataFieldValues;
    }

    public void setMetadataFieldValues(String metadataFieldValues) {
        this.metadataFieldValues = metadataFieldValues;
    }

    public CategoryMetadataDto(String metadataField, String metadataFieldValues) {
        this.metadataField = metadataField;
        this.metadataFieldValues = metadataFieldValues;
    }

    public CategoryMetadataDto() {
    }
}
