package com.example.diploma.configuration;

import com.example.diploma.clients.BrokerClient;
import com.example.diploma.clients.binance.BinanceClient;
import com.example.diploma.configuration.properties.BinanceProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.net.http.HttpClient;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BrokerClient binanceBrokerClient(HttpClient httpClient, ObjectMapper objectMapper, ModelMapper modelMapper,
                                            BinanceProperties binanceProperties) {
        return new BinanceClient(httpClient, objectMapper, modelMapper, binanceProperties);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
