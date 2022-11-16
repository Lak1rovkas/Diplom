package com.example.diploma.mapper.binance;

import com.example.diploma.clients.binance.dto.KLinesDTO;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.modelmapper.AbstractConverter;

public class JsonNodeToKLinesDTOMapper extends AbstractConverter<ArrayNode, KLinesDTO> {
    @Override
    protected KLinesDTO convert(ArrayNode jsonNode) {
        KLinesDTO result = new KLinesDTO();
        result.setOpenTime(jsonNode.get(0).asLong());
        result.setOpen(jsonNode.get(1).asDouble());
        result.setHigh(jsonNode.get(2).asDouble());
        result.setLow(jsonNode.get(3).asDouble());
        result.setClose(jsonNode.get(4).asDouble());
        result.setVolume(jsonNode.get(5).asDouble());
        result.setCloseTime(jsonNode.get(6).asLong());
        result.setQuoteAssetVolume(jsonNode.get(7).asDouble());
        result.setNumberOfTrades(jsonNode.get(8).asInt());
        result.setTakerBuyBaseAssetVolume(jsonNode.get(9).asDouble());
        result.setTakerBuyQuoteAssetVolume(jsonNode.get(10).asDouble());
        result.setCanBeIgnored(jsonNode.get(11).asBoolean());

        return result;
    }
}
