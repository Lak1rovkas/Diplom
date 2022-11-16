package com.example.diploma.clients.binance;

import com.example.diploma.clients.BrokerClient;
import com.example.diploma.clients.binance.dto.KLinesDTO;
import com.example.diploma.configuration.properties.BinanceProperties;
import com.example.diploma.exception.TooManyRequestsException;
import com.example.diploma.model.KLines;
import com.example.diploma.model.Symbol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
public class BinanceClient implements BrokerClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final BinanceProperties properties;

    @Override
    public List<KLines> getKLines(Symbol symbol, LocalDate startTime, LocalDate endTime) {
        URI url = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + properties.getKLinesUrl())
                .queryParam("symbol", symbol.getSymbol())
                .queryParam("startTime", startTime.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .queryParam("endTime", endTime.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .queryParam("interval", properties.getInterval())
                .build()
                .toUri();
        log.debug("Url: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = sentRequest(request);

        JsonNode responseObj;
        try {
            responseObj = objectMapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to read response data", e);
        }

        List<KLinesDTO> dtoResponse = new ArrayList<>(responseObj.size());
        try {
            responseObj.forEach(node -> dtoResponse.add(modelMapper.map(node, KLinesDTO.class)));
        } catch (Exception e) {
            throw new RuntimeException("Unable to read response data", e);
        }

        return dtoResponse.stream()
                .map(dto -> modelMapper.map(dto, KLines.class))
                .peek(kLine -> kLine.setSymbol(symbol))
                .collect(Collectors.toList());
    }

    public Set<String> getAllSymbols() {
        URI uri = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + properties.getSymbolUrl())
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> response = sentRequest(request);

        JsonNode responseObj;
        try {
            responseObj = objectMapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to read response data", e);
        }

        JsonNode symbolNodes = responseObj.get("symbols");

        Set<String> symbols = new HashSet<>();
        for (var symbolNode : symbolNodes) {
            if (symbolNode.get("quoteAsset").textValue().equals("USDT")) {
                symbols.add(symbolNode.get("symbol").textValue());
            }
        }

        return symbols;
    }

    private HttpResponse<String> sentRequest(HttpRequest request) {
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Unable to get response from Binance", e);
        }
        if (response.statusCode() == HttpStatus.TOO_MANY_REQUESTS.value()) {
            throw new TooManyRequestsException(String.format("We sent too many requests to %s. Need to cool down",
                    request.uri().toString()));
        } else if (response.statusCode() != HttpStatus.OK.value()) {
            throw new IllegalStateException(String.format("Unable to fetch data from %s. Response code is %s",
                    request.uri().toString(), response.statusCode()));
        }
        return response;
    }
}
