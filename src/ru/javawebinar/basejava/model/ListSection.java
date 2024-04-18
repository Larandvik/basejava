package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> items;

    public ListSection(String... items) {
        this.items = List.of(items);
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("* ");
            sb.append(item);
        }
        sb.append("\n");
        return sb.toString();
    }
}
