package com.bootcamp.bootcampecomproject.entities;

import javax.persistence.*;

@Entity
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private Integer quantityAvailable;
    private Double price;

    private String productAttributesJSON;

//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> productAttributes;

    private String primaryImageName;
    private Boolean isActive;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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



    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getProductAttributesJSON() {
        return productAttributesJSON;
    }

    public void setProductAttributesJSON(String productAttributesJSON) {
        this.productAttributesJSON = productAttributesJSON;
    }

//    public Map<String, Object> getProductAttributes() {
//        return productAttributes;
//    }
//
//    public void setProductAttributes(Map<String, Object> productAttributes) {
//        this.productAttributes = productAttributes;
//    }

//    public ObjectMapper getObjectMapper() {
//        return objectMapper;
//    }
//
//    public void setObjectMapper(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

//    public void serializeProductAttributes() throws JsonProcessingException {
//        this.productAttributesJSON = objectMapper.writeValueAsString(productAttributes);
//    }
//    public void deserializeProductAttributes() throws IOException {
//        this.productAttributes = objectMapper.readValue(productAttributesJSON, HashMap.class);
//    }
}
