package com.airqualityservice.service;

import com.airqualityservice.dto.AirQualityDto;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiCaller;
import com.airqualityservice.infrastructure.api.seoul.SeoulAirQualityApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeoulService {

    private static final String GOOD = "좋음";
    private static final String NORMAL = "보통";
    private static final String BAD = "나쁨";
    private static final String WORST = "매우나쁨";

    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityDto seoulMapper() {

        SeoulAirQualityApiDto.GetAirQualityResponse airQuality = seoulAirQualityApiCaller.getAirQuality();
        List<AirQualityDto.Borough> boroughList = new ArrayList<>();
        AirQualityDto.Borough[] boroughs;
        float sum = 0;

        for (SeoulAirQualityApiDto.Item item : airQuality.getResult().getItems()) {
            AirQualityDto.Borough borough = AirQualityDto.Borough.builder()
                    .name(item.getArea())
                    .pm25(item.getPm25())
                    .gradePm25(pm25Grade(item.getPm25()))
                    .pm10(item.getPm10())
                    .gradePm10(pm10Grade(item.getPm10()))
                    .o3(item.getO3())
                    .gradeO3(o3Grade(item.getO3()))
                    .no2(item.getNo2())
                    .gradeNo2(no2Grade(item.getNo2()))
                    .co(item.getCo())
                    .gradeCo(coGrade(item.getCo()))
                    .so2(item.getSo2())
                    .gradeSo2(so2Grade(item.getSo2()))
                    .build();

            boroughList.add(borough);
            sum += item.getPm10();
        }

        boroughs = new AirQualityDto.Borough[boroughList.size()];
        for (int i = 0; i < boroughList.size(); i++) {
            boroughs[i] = boroughList.get(i);
        }

        return AirQualityDto.builder()
                .elements(boroughs)
                .sido("서울")
                .averagePm10(sum / boroughs.length)
                .averagePm10Grade(pm10Grade(sum / boroughs.length))
                .totalCount(boroughs.length)
                .build();
    }

    private String pm25Grade(float data) {
        if (data > 0 && data <= 15) return GOOD;
        else if (data > 15 && data <= 35) return NORMAL;
        else if (data > 35 && data <= 75) return BAD;
        else return WORST;
    }

    private String pm10Grade(float data) {
        if (data > 0 && data <= 30) return GOOD;
        else if (data > 30 && data <= 80) return NORMAL;
        else if (data > 80 && data <= 150) return BAD;
        else return WORST;
    }

    private String o3Grade(float data) {
        if (data > 0 && data <= 0.03) return GOOD;
        else if (data > 0.03 && data <= 0.09) return NORMAL;
        else if (data > 0.09 && data <= 0.15) return BAD;
        else return WORST;
    }

    private String no2Grade(float data) {
        if (data > 0 && data <= 0.03) return GOOD;
        else if (data > 0.03 && data <= 0.06) return NORMAL;
        else if (data > 0.06 && data <= 0.2) return BAD;
        else return WORST;
    }

    private String coGrade(float data) {
        if (data > 0 && data <= 2) return GOOD;
        else if (data > 2 && data <= 9) return NORMAL;
        else if (data > 9 && data <= 15) return BAD;
        else return WORST;
    }

    private String so2Grade(float data) {
        if (data > 0 && data <= 0.02) return GOOD;
        else if (data > 0.02 && data <= 0.05) return NORMAL;
        else if (data > 0.05 && data <= 0.15) return BAD;
        else return WORST;
    }
}
