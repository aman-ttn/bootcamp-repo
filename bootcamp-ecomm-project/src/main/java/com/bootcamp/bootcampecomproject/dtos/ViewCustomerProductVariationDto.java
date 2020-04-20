package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.utils.HashMapConverter;

import javax.persistence.Convert;
import java.util.Map;
import java.util.Set;

public class ViewCustomerProductVariationDto {
    private Long variationId;
    private Double variationPrice;
    private Integer variationQuantityAvailable;
    private Boolean isVariationActive;
    private String primaryImageName;
    private Set<String> secondaryImageName;
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> metadata;

    public Long getVariationId() {
        return variationId;
    }

    public void setVariationId(Long variationId) {
        this.variationId = variationId;
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

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
