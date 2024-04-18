package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {
    private final LocalDate start;
    private final LocalDate end;
    private final String title;
    private String description;

    public Period(String start, String end, String title, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.start = LocalDate.parse("01/" + start, formatter);
        this.end = LocalDate.parse("01/" + end, formatter);
        this.title = title;
        this.description = description;
    }

    public Period(String start, String end, String title) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.start = LocalDate.parse("01/" + start, formatter);
        this.end = LocalDate.parse("01/" + end, formatter);
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(start, period.start) && Objects.equals(end, period.end) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, title, description);
    }

    @Override
    public String toString() {
        return start + " - " + end + "\n" +
                title + "\n" +
                description + "\n";
    }
}
