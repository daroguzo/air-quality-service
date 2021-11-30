package com.airqualityservice.application.service;

import com.airqualityservice.application.SidoType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KoreaAirQualityServiceFactory {

     private final Map<SidoType, KoreaAirQualityService> serviceMap = new HashMap<>();

     public KoreaAirQualityServiceFactory(List<KoreaAirQualityService> serviceList) {
          for (var service : serviceList) {
               serviceMap.put(service.getSidoType(), service);
          }
     }

     public KoreaAirQualityService getService(SidoType sidoType) {
          return Optional.of(serviceMap.get(sidoType))
                  .orElseThrow(() -> new RuntimeException("대기질 정보를 조회할 수 없는 시/도 입니다."));
     }
}
