package com.airqualityservice.application.controller;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.application.service.AirQualityService;
import com.airqualityservice.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/air_quality", produces = "application/json;charset=utf-8")
@RequiredArgsConstructor
public class AirQualityController {

    private final AirQualityService seoulService;

    @GetMapping("/{sidoCode}")
    public AirQualityDto getAirQuality(@PathVariable("sidoCode") SidoType sidoCode,
                                       @RequestParam(required = false) String gu) {
        return seoulService.getAirQuality(sidoCode, gu);
    }
}