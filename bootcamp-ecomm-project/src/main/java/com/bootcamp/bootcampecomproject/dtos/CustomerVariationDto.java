package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.utils.HashMapConverter;

import javax.persistence.Convert;
import java.util.Map;

public class CustomerVariationDto {
    private Long productVariationId;
    private String primaryImage;
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> metadata;

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
