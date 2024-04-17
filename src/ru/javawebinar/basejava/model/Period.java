package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Period {
    private YearMonth start;
    private YearMonth end;
    private String title;
    private String description;

    public Period(String startPeriod, String endPeriod, String title, String description) {
        this.start = YearMonth.parse(startPeriod, DateTimeFormatter.ofPattern("MM/yyyy"));
        this.end = YearMonth.parse(endPeriod, DateTimeFormatter.ofPattern("MM/yyyy"));
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return start + " - " + end + "\n" +
                title + "\n" +
                description + "\n";
    }
}
