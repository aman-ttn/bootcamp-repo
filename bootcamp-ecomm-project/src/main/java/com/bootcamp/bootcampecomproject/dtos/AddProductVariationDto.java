package com.bootcamp.bootcampecomproject.dtos;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class AddProductVariationDto {
//    @NotNull
    private Long productId;
//    @NotNull
    private Map<String,String> metadata;
    private Integer quantityAvailable;
    private Double price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
