package com.example.diploma.mapper;

import org.modelmapper.AbstractConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LongToLocalDateTimeConverter extends AbstractConverter<Long, LocalDateTime> {
    @Override
    protected LocalDateTime convert(Long source) {
        if (source == null) {
            return null;
        }

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(source), TimeZone.getDefault().toZoneId());
    }
}
