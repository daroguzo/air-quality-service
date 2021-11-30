package com.airqualityservice.infrastructure.api.seoul;

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
public class SeoulAirQualityApiCaller implements KoreaAirQualityService {

    private final SeoulAirQualityApi seoulAirQualityApi;

    public SeoulAirQualityApiCaller(@Value("${api.seoul.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.seoulAirQualityApi = retrofit.create(SeoulAirQualityApi.class);
    }

    @Override
    public SidoType getSidoType() {
        return SidoType.seoul;
    }

    public AirQualityDto getAirQualityInfo() {
        try {
            var call = seoulAirQualityApi.getAirQuality();
            var response = call.execute().body();
            System.out.println(response.getResult().toString());

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


    private AirQualityDto convert(SeoulAirQualityApiDto.GetAirQualityResponse response) {
        List<AirQualityDto.GuAirQualityInfo> guAirQualityInfoList = new ArrayList<>();
        float sum = 0;

        for (SeoulAirQualityApiDto.Item item : response.getResult().getItems()) {
            AirQualityDto.GuAirQualityInfo guAirQualityInfo = AirQualityDto.GuAirQualityInfo.builder()
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

            guAirQualityInfoList.add(guAirQualityInfo);
            sum += item.getPm10();
        }

        return AirQualityDto.builder()
                .guList(guAirQualityInfoList)
                .sido("서울시")
                .averagePm10(sum / guAirQualityInfoList.size())
                .averagePm10Grade(getPm10Grade((int)(sum / guAirQualityInfoList.size())))
                .totalCount(guAirQualityInfoList.size())
                .build();
    }
}
