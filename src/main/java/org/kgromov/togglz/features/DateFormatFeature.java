package org.kgromov.togglz.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_WEEK_DATE;

public enum DateFormatFeature implements Feature {

    @EnabledByDefault
    @Label("ISO local date format - 'yyyy-MM-dd'")
    ISO_LOCAL_DATE_FEATURE(ISO_LOCAL_DATE),

    @Label("ISO week date format - 'yyyy-MM-dd'")
    ISO_WEEK_DATE_FEATURE(ISO_WEEK_DATE),

    @Label("Custom date format - 'dd-MM-yyyy'")
   CUSTOM_DATE_FEATURE(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    private final DateTimeFormatter formatter;

    DateFormatFeature(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
