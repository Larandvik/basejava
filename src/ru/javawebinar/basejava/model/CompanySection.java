package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Company> companies = new ArrayList<>();

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public CompanySection() {
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void addCompanies(Company company) {
        companies.add(company);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(companies);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company company : companies) {
            sb.append(company);
        }
        return sb.toString();
    }
}
