package hu.rbr.sfinapp.core.api;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class DatesTest {

    @Test
    public void parseValidDate() {
        Date parsed = Dates.parseAsDate("2014-07-05");

        Instant expected = LocalDate.of(2014, 7, 5).atStartOfDay(ZoneId.systemDefault()).toInstant();
        assertThat(parsed, equalTo(Date.from(expected)));
    }

    @Test
    public void parseNullDate() {
        Date parsed = Dates.parseAsDate(null);
        assertThat(parsed, nullValue());
    }

    @Test(expected = DateTimeParseException.class)
    public void parseInvalidDate() {
        Dates.parseAsDate("Jan 08 2014");
    }

}
