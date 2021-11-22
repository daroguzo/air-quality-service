package com.airqualityservice.application.service;

import com.airqualityservice.application.AirQualityGrade;
import com.airqualityservice.application.util.AirQualityGradeUtil;
import com.airqualityservice.dto.AirQualityDto;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.airqualityservice.application.util.AirQualityGradeUtil.*;

@Service
@RequiredArgsConstructor
public class SeoulService {

    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityDto seoulMapper() {

        SeoulAirQualityApiDto.GetAirQualityResponse airQuality = seoulAirQualityApiCaller.getAirQuality();
        List<AirQualityDto.Gu> guList = new ArrayList<>();
        AirQualityDto.Gu[] gu;
        float sum = 0;

        for (SeoulAirQualityApiDto.Item item : airQuality.getResult().getItems()) {
            AirQualityDto.Gu borough = AirQualityDto.Gu.builder()
                    .name(item.getArea())
                    .pm25(item.getPm25())
                    .gradePm25(getPm25Grade(item.getPm25()))
                    .pm10(item.getPm10())
                    .gradePm10(getPm10Grade(item.getPm10()))
                    .o3(item.getO3())
                    .gradeO3(getO3Grade(item.getO3()))
                    .no2(item.getNo2())
                    .gradeNo2(getNo2Grade(item.getNo2()))
                    .co(item.getCo())
                    .gradeCo(getCoGrade(item.getCo()))
                    .so2(item.getSo2())
                    .gradeSo2(getSo2Grade(item.getSo2()))
                    .build();

            guList.add(borough);
            sum += item.getPm10();
        }

        gu = new AirQualityDto.Gu[guList.size()];
        for (int i = 0; i < guList.size(); i++) {
            gu[i] = guList.get(i);
        }

        return AirQualityDto.builder()
                .elements(gu)
                .sido("서울시")
                .averagePm10(sum / gu.length)
                .averagePm10Grade(getPm10Grade((int)(sum / gu.length)))
                .totalCount(gu.length)
                .build();
    }
}
