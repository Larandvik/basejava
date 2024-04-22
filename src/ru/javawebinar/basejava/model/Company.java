package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    private final String name;
    private final String website;
    private final List<Period> periods = new ArrayList<>();

    public Company(String name, String website, Period period) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.website = website;
        periods.add(period);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
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
