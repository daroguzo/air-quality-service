package com.airqualityservice.application.util;

import com.airqualityservice.application.AirQualityGrade;
import lombok.NoArgsConstructor;

import static com.airqualityservice.application.AirQualityGrade.*;

@NoArgsConstructor
public class AirQualityGradeUtil {

    public static AirQualityGrade getPm25Grade(Integer pm25) {
        if (pm25 <= 15) {
            return 좋음;
        }
        if (pm25 <= 35) {
            return 보통;
        }
        if (pm25 <= 75) {
            return 나쁨;
        }
        return 매우나쁨;
    }

    public static AirQualityGrade getPm10Grade(Integer pm10) {
        if (pm10 <= 30) {
            return 좋음;
        }
        if (pm10 <= 80) {
            return 보통;
        }
        if (pm10 <= 150) {
            return 나쁨;
        }
        return 매우나쁨;
    }

    public static AirQualityGrade getO3Grade(Float o3) {
        if (o3 <= 0.030) {
            return 좋음;
        }
        if (o3 <= 0.090) {
            return 보통;
        }
        if (o3 <= 0.150) {
            return 나쁨;
        }
        return 매우나쁨;
    }

    public static AirQualityGrade getNo2Grade(Float no2) {
        if (no2 <= 0.030) {
            return 좋음;
        }
        if (no2 <= 0.060) {
            return 보통;
        }
        if (no2 <= 0.200) {
            return 나쁨;
        }
        return 매우나쁨;
    }

    public static AirQualityGrade getCoGrade(Float co) {
        if (co <= 2) {
            return 좋음;
        }
        if (co <= 9) {
            return 보통;
        }
        if (co <= 15) {
            return 나쁨;
        }
        return 매우나쁨;
    }

    public static AirQualityGrade getSo2Grade(Float so2) {
        if (so2 <= 0.020) {
            return 좋음;
        }
        if (so2 <= 0.050) {
            return 보통;
        }
        if (so2 <= 0.150) {
            return 나쁨;
        }
        return 매우나쁨;
    }
}
