package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> items;

    public ListSection(String... items) {
        this.items = List.of(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("* ");
            sb.append(item);
        }
        return sb.toString();
    }
}
