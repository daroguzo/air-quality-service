package com.airqualityservice.application.service;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.dto.AirQualityDto;

public interface KoreaAirQualityService {

    SidoType getSidoType();

    AirQualityDto getAirQualityInfo();
}
