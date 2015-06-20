package hu.rbr.sfinapp.core.api;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Dates {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static Date parseAsDate(String str) {
        if (str == null) {
            return null;
        }

        LocalDate localDate = parseAsLocalDate(str);
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate parseAsLocalDate(String str) {
        if (str == null) {
            return null;
        }

        return LocalDate.parse(str, FORMATTER);
    }

}
