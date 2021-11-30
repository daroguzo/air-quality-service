package com.airqualityservice.application.controller;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.application.service.AirQualityService;
import com.airqualityservice.application.util.DateUtil;
import com.airqualityservice.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/air-quality", produces = "application/json;charset=utf-8")
public class AirQualityController {

    private final AirQualityService airQualityService;

    @GetMapping("/{sidoCode}")
    public AirQualityDto getAirQuality(@PathVariable("sidoCode") SidoType sidoCode,
                                       @RequestParam(required = false, defaultValue = "all") String gu) {
        return airQualityService.getAirQualityInfo(sidoCode, gu, DateUtil.getDateHourString());
    }
}