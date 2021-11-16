package com.airqualityservice.seoul;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

public class SeoulAirQualityApiDto {
    @Getter
    @Setter
    @ToString
    public static class GetAirQualityResponse {
        @JsonProperty("RealtimeCityAir")
        private Result result;
    }

    @Getter
    @Setter
    @ToString
    public static class Result {
        @JsonProperty("RESULT")
        private Header header;
        @JsonProperty("row")
        private List<Item> items;
        @JsonProperty("list_total_count")
        private Integer totalCount;

        public boolean isSuccess() {
            if (Objects.equals(header.getCode(), "INFO-000")) {
                return true;
            }
            return false;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Header {
        @JsonProperty("CODE")
        private String code;
        @JsonProperty("MESSAGE")
        private String message;
    }

    @Getter
    @Setter
    @ToString
    public static class Item {
        @JsonProperty("MSRRGN_NM")
        private String site;
        @JsonProperty("MSRSTE_NM")
        private String areaIndex;
        @JsonProperty("MSRDT")
        private String measurementTime;
        @JsonProperty("IDEX_NM")
        private String totalAirGrade;
        @JsonProperty("IDEX_MVL")
        private Float totalAirIndex;
        @JsonProperty("ARPLT_MAIN")
        private String mainMaterial;
        @JsonProperty("PM10")
        private Float pm10;
        @JsonProperty("PM25")
        private Float pm25;
        @JsonProperty("O3")
        private Float o3;
        @JsonProperty("NO2")
        private Float no2;
        @JsonProperty("CO")
        private Float co;
        @JsonProperty("SO2")
        private Float so2;
    }
}
