package com.example.config;

import com.example.converter.StringToSearchFacadeRequestFilterConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Registers custom conversion type to handle mapping of "filter" request parameter.
 */
@Configuration
public class ConverterConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public ConverterConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSearchFacadeRequestFilterConverter(objectMapper));
    }
}
