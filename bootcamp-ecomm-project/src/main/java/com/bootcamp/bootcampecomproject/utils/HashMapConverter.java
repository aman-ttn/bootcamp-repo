package com.bootcamp.bootcampecomproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String,Object>,String>  {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> productInfo) {
        String productInfoJson = null;
        try {
            productInfoJson = objectMapper.writeValueAsString(productInfo);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return productInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String productInfoJSON) {
        Map<String, Object> productInfo = null;
        try {
            productInfo = objectMapper.readValue(productInfoJSON, Map.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return productInfo;
    }

}
