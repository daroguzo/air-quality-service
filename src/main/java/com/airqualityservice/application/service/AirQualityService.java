package com.airqualityservice.application.service;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final AirQualityDtoFactory airQualityServiceFactory;

    public AirQualityDto getAirQuality(SidoType sido, String gu) {
        return airQualityServiceFactory.getAirQualityDto(sido, gu);
    }
}
