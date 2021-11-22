package com.airqualityservice.application.service;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.dto.AirQualityDto;
import com.airqualityservice.infrastructure.api.busan.BusanAirQualityApiCaller;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirQualityDtoFactory {

     private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;
     private final BusanAirQualityApiCaller busanAirQualityApiCaller;

     public AirQualityDto getAirQualityDto(SidoType sidoType, String gu) {
          AirQualityDto airQualityDto;

          switch (sidoType) {
               case seoul:
                    airQualityDto =  seoulAirQualityApiCaller.getAirQualityDto();
                    break;
               case busan:
                    airQualityDto = busanAirQualityApiCaller.getAirQualityDto();
                    break;
               default:
                    throw new IllegalArgumentException("지원하지 않는 도시입니다.");
          }

          if (gu != null) {
               return airQualityDto.searchByGu(gu);
          }

          return airQualityDto;
     }
}
