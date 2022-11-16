package com.example.diploma.configuration;

import com.example.diploma.mapper.LongToLocalDateTimeConverter;
import com.example.diploma.mapper.binance.JsonNodeToKLinesDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);

        modelMapper.addConverter(new LongToLocalDateTimeConverter());
        modelMapper.addConverter(new JsonNodeToKLinesDTOMapper());

        return modelMapper;
    }
}
