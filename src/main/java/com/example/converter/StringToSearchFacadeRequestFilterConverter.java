package com.example.converter;

import com.example.controller.request.SearchFacadeRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.convert.converter.Converter;

/**
 * Converts JSON-encoded string to a {@link SearchFacadeRequestFilter} object.
 */
public class StringToSearchFacadeRequestFilterConverter implements Converter<String, SearchFacadeRequestFilter> {

    private final ObjectMapper objectMapper;

    public StringToSearchFacadeRequestFilterConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public SearchFacadeRequestFilter convert(String source) {
        SearchFacadeRequestFilter output = null;
        try {
            output = objectMapper.readValue(source, SearchFacadeRequestFilter.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return output;
    }
}
