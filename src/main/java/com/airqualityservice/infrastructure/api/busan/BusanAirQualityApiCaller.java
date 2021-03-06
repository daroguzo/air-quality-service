package com.airqualityservice.infrastructure.api.busan;

import com.airqualityservice.application.SidoType;
import com.airqualityservice.application.service.KoreaAirQualityService;
import com.airqualityservice.dto.AirQualityDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.airqualityservice.application.util.AirQualityGradeUtil.*;

@Slf4j
@Component
public class BusanAirQualityApiCaller implements KoreaAirQualityService {
    private final BusanAirQualityApi busanAirQualityApi;

    public BusanAirQualityApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanAirQualityApi.class);
    }

    @Override
    public SidoType getSidoType() {
        return SidoType.busan;
    }

    public AirQualityDto getAirQualityInfo() {
        try {
            var call = busanAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResult().isSuccess()) {
                log.info(response.toString());
                return convert(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. header=" + response.getResult().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    private AirQualityDto convert(BusanAirQualityApiDto.GetAirQualityResponse response) {
        List<AirQualityDto.GuAirQualityInfo> guAirQualityInfoList = new ArrayList<>();
        float sum = 0;
        int pm10ErrorCount = 0;

        for (BusanAirQualityApiDto.Item item : response.getResult().getItems()) {
            // 오류 지역 제외
            if (!item.getPm10().chars().allMatch(Character::isDigit)) {
             pm10ErrorCount++;
             item.setPm10("0");
            }
            AirQualityDto.GuAirQualityInfo guAirQualityInfo = AirQualityDto.GuAirQualityInfo.builder()
                    .name(item.getSite())
                    .pm25(Integer.parseInt(item.getPm25()))
                    .gradePm25(getPm25Grade(Integer.parseInt(item.getPm25())))
                    .pm10(Integer.parseInt(item.getPm10()))
                    .gradePm10(getPm10Grade(Integer.parseInt(item.getPm10())))
                    .o3(Float.parseFloat(item.getO3()))
                    .gradeO3(getO3Grade(Float.parseFloat(item.getO3())))
                    .no2(Float.parseFloat(item.getNo2()))
                    .gradeNo2(getNo2Grade(Float.parseFloat(item.getNo2())))
                    .co(Float.parseFloat(item.getCo()))
                    .gradeCo(getCoGrade(Float.parseFloat(item.getCo())))
                    .so2(Float.parseFloat(item.getSo2()))
                    .gradeSo2(getSo2Grade(Float.parseFloat(item.getSo2())))
                    .build();

            guAirQualityInfoList.add(guAirQualityInfo);
            sum += Integer.parseInt(item.getPm10());
        }

        return AirQualityDto.builder()
                .guList(guAirQualityInfoList)
                .sido("부산시")
                .averagePm10(sum / guAirQualityInfoList.size())
                .averagePm10Grade(getPm10Grade((int)(sum / guAirQualityInfoList.size() - pm10ErrorCount)))
                .totalCount(guAirQualityInfoList.size())
                .build();
    }
}
