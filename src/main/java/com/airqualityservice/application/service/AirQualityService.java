package com.airqualityservice.application.service;

import com.airqualityservice.application.Sido;
import com.airqualityservice.dto.AirQualityDto;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityDto getAirQualityInfo(Sido sido, String gu) {
        if (sido == Sido.seoul) {
            var airQualityInfo = seoulAirQualityApiCaller.getAirQuality();
            if (gu != null) {
                return airQualityInfo.searchByGu(gu);
            }
            return airQualityInfo;
        }

        throw new RuntimeException(sido + " 대기질 정보는 아직 준비중입니다.");
    }
}
