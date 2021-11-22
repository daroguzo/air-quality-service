package com.airqualityservice.dto;

import com.airqualityservice.application.AirQualityGrade;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class AirQualityDto {

    public AirQualityDto searchByGu(String gu) {
        if (gu == null) {
            return this;
        }
        var searchedGuInfo = searchGuAirQualityInfo(gu);
        guList = Collections.singletonList(searchedGuInfo);
        return this;
    }

    private Gu searchGuAirQualityInfo(String gu) {
        return guList.stream()
                .filter(guAirQualityInfo -> guAirQualityInfo.getName().equals(gu))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(gu + "에 해당하는 자치구가 존재하지 않습니다."));
    }

    List<Gu> guList;
    String sido;
    float averagePm10;
    AirQualityGrade averagePm10Grade;
    int totalCount;

    @Getter
    @Setter
    @Builder
    public static class Gu {
        String name;

        int pm25;
        AirQualityGrade gradePm25;
        int pm10;
        AirQualityGrade gradePm10;
        float o3;
        AirQualityGrade gradeO3;
        float no2;
        AirQualityGrade gradeNo2;
        float co;
        AirQualityGrade gradeCo;
        float so2;
        AirQualityGrade gradeSo2;
    }
}
