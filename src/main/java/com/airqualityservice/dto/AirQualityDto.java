package com.airqualityservice.dto;

import com.airqualityservice.application.AirQualityGrade;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AirQualityDto {
    Gu[] elements;
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
