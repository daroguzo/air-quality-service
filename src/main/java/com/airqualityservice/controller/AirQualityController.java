package com.airqualityservice.controller;

import com.airqualityservice.dto.AirQualityDto;
import com.airqualityservice.service.SeoulService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/air_quality", produces = "application/json;charset=utf-8")
@RequiredArgsConstructor
public class AirQualityController {

    private final SeoulService seoulService;

    @GetMapping("/seoul")
    public AirQualityDto seoul() {
        return seoulService.seoulMapper();
    }
}