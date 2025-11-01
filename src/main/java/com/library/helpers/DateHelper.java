package com.library.helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    public static String toIso8601(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDateTime dateStringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime local = LocalDateTime.parse(dateString, formatter);

        ZonedDateTime zoned = local.atZone(ZoneId.of("America/Sao_Paulo"));

        return zoned.toLocalDateTime();
    }
}
