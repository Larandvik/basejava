package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private String description;

    public Period(String startDate, String endDate, String title, String description) {
        Objects.requireNonNull(startDate, "start must not be null");
        Objects.requireNonNull(endDate, "end must not be null");
        Objects.requireNonNull(title, "title must not be null");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.startDate = LocalDate.parse("01/" + startDate, formatter);
        this.endDate = LocalDate.parse("01/" + endDate, formatter);
        this.title = title;
        this.description = description;
    }

    public Period(String startDate, String endDate, String title) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.startDate = LocalDate.parse("01/" + startDate, formatter);
        this.endDate = LocalDate.parse("01/" + endDate, formatter);
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
        if (!(o instanceof Period period)) return false;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(startDate).append(" - ").append(endDate).append("\n");
        sb.append(title).append("\n");
        if (description != null) {
            sb.append(description);
        }
        return sb.toString();
    }
}
