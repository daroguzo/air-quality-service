package com.airqualityservice.application.service;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.dto.AirQualityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirQualityService {

    private final KoreaAirQualityServiceFactory koreaAirQualityServiceFactory;

    @Cacheable(value = "airQualityInfo", key = "#sidoType +'-'+ #gu + '-' + #dateHourString")
    public AirQualityDto getAirQualityInfo(SidoType sidoType, String gu, String dateHourString) {
        KoreaAirQualityService service = koreaAirQualityServiceFactory.getService(sidoType);
        var airQualityInfo = service.getAirQualityInfo();
        if (gu.equals("all") == false) {
            return airQualityInfo.searchByGu(gu);
        }
        return airQualityInfo;
    }

    // 매 정각마다 모든 캐시 초기화
    @Scheduled(cron = "0 0 0/1 * * *")
    @CacheEvict(value = "airQualityInfo", allEntries = true)
    public void updateAirQualityInfo() {

    }

}
