package ru.javawebinar.basejava.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType (XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String website;
    private final List<Period> periods = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String website, Period period) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.website = website;
        periods.add(period);
    }

    public Company(String name, String website, List<Period> periods) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.website = website;
        periods.addAll(periods);
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(name, company.name) && Objects.equals(website, company.website) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, periods);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n");
        for (Period period : periods) {
            result.append(period.toString());
        }
        return result.toString();
    }
}
