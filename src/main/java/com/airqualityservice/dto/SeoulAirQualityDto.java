package com.airqualityservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class SeoulAirQualityDto {
    Borough[] elements;
    String region;
    float averageDust;
    String averageGrade;
    int totalCount;

    @Getter
    @Setter
    @Builder
    public static class Borough {
        String name;

        float pm25;
        String gradePm25;
        float pm10;
        String gradePm10;
        float o3;
        String gradeO3;
        float no2;
        String gradeNo2;
        float co;
        String gradeCo;
        float so2;
        String gradeSo2;
    }
}
