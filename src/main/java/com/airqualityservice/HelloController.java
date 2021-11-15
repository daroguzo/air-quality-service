package com.airqualityservice;

import com.airqualityservice.busan.BusanAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final BusanAirQualityApiCaller busanAirQualityApiCaller;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("busan")
    public String busan() {
        System.out.println(busanAirQualityApiCaller.getAirQuality());

        return "busan";
    }
}
