package com.bootcamp.bootcampecomproject.persistJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Convert;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SerializeDeserializeObject {
    @Autowired
    private ObjectMapper objectMapper;
    private String productAttributesJSON;

    private Map<String, Object> productAttributes;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getProductAttributesJSON() {
        return productAttributesJSON;
    }

    public void setProductAttributesJSON(String productAttributesJSON) {
        this.productAttributesJSON = productAttributesJSON;
    }

    public Map<String, Object> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Map<String, Object> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public void serializeProductAttributes() throws JsonProcessingException {
        this.productAttributesJSON = objectMapper.writeValueAsString(productAttributes);
    }
    public void deserializeProductAttributes() throws IOException {
        this.productAttributes = objectMapper.readValue(productAttributesJSON, HashMap.class);
    }
}
