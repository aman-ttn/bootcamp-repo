package com.bootcamp.bootcampecomproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<String,String>,String>  {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, String> productInfo) {
        String productInfoJson = null;
        try {
            productInfoJson = objectMapper.writeValueAsString(productInfo);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return productInfoJson;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String productInfoJSON) {
        Map<String, String> productInfo = null;
        try {
            productInfo = objectMapper.readValue(productInfoJSON, Map.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return productInfo;
    }

}
