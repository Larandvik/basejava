package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("HomePage");

    private final String contact;

    ContactType(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return contact;
    }
}