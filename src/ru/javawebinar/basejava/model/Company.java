package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private String website;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String website, Period period) {
        this.name = name;
        this.website = website;
        periods.add(period);
    }

    @Override
    public String toString() {
        return name + "\n" +
                periods;
    }
}
