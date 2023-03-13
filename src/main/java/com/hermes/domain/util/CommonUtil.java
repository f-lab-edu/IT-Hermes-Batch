package com.hermes.domain.util;

import java.time.LocalDateTime;

public class CommonUtil {
    public static LocalDateTime parseLocalDateTime(String date) {
        String[] dateArray = date.split("-");
        LocalDateTime localDateTime = LocalDateTime.of(
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[3]),
                Integer.parseInt(dateArray[4]),
                Integer.parseInt(dateArray[5]));
        return localDateTime;
    }
}
