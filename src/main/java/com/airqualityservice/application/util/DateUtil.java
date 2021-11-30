package com.airqualityservice.application.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_HOUR_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddhh");

    private DateUtil() {
    }

    public static String getDateHourString() {
        return LocalDateTime.now().format(DATE_HOUR_FORMAT);
    }
}
