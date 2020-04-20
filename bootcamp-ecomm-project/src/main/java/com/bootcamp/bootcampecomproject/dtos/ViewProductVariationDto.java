package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.utils.HashMapConverter;

import javax.persistence.Convert;
import java.util.Map;
import java.util.Set;

public class ViewProductVariationDto {

    private Long productId;
    private String productName;
    private String productBrand;
    private String productDescription;
    private Double variationPrice;
    private Integer variationQuantityAvailable;
    private Boolean isVariationActive;
    private String primaryImageName;
    private Set<String> secondaryImageName;
    private Boolean isCancellable;
    private Boolean isReturnable;

    @Convert(converter = HashMapConverter.class)
    private Map<String, String> metadata;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getVariationPrice() {
        return variationPrice;
    }

    public void setVariationPrice(Double variationPrice) {
        this.variationPrice = variationPrice;
    }

    public Integer getVariationQuantityAvailable() {
        return variationQuantityAvailable;
    }

    public void setVariationQuantityAvailable(Integer variationQuantityAvailable) {
        this.variationQuantityAvailable = variationQuantityAvailable;
    }

    public Boolean getVariationActive() {
        return isVariationActive;
    }

    public void setVariationActive(Boolean variationActive) {
        isVariationActive = variationActive;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public Set<String> getSecondaryImageName() {
        return secondaryImageName;
    }

    public void setSecondaryImageName(Set<String> secondaryImageName) {
        this.secondaryImageName = secondaryImageName;
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

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
