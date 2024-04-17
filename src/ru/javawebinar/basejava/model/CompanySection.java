package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends AbstractSection {
    List<Company> companies = new ArrayList<>();

    public CompanySection(String name, String website, String startPeriod, String endPeriod,
                             String title, String description) {
        companies.add(new Company(name, website, new Period(startPeriod, endPeriod, title, description)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company company : companies) {
            sb.append("* ");
            sb.append(company);
        }
        return sb.toString();
    }
}
