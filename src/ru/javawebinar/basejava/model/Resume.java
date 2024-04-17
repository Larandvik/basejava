package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacts;
    private Map<SectionType, AbstractSection> sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
        this.contacts = new HashMap<>();
        this.sections = new HashMap<>();
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = new HashMap<>();
        this.sections = new HashMap<>();
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void addSection(SectionType sectionType, AbstractSection section) {
        sections.put(sectionType, section);
    }

    public void addContact(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    public void addPhone(String phoneNumber) {
        contacts.put(ContactType.PHONE, phoneNumber);
    }

    public void addSkype(String url) {
        contacts.put(ContactType.SKYPE, url);
    }

    public void addEmail(String url) {
        contacts.put(ContactType.EMAIL, url);
    }

    public void addLinkedIn(String url) {
        contacts.put(ContactType.LINKEDIN, url);
    }

    public void addGitHub(String url) {
        contacts.put(ContactType.GITHUB, url);
    }

    public void addStackOverFlow(String url) {
        contacts.put(ContactType.STACKOVERFLOW, url);
    }

    public void addHomePage(String url) {
        contacts.put(ContactType.HOMEPAGE, url);
    }

    public void setSections(Map<SectionType, AbstractSection> sections) {
        this.sections = sections;
    }

    public AbstractSection getSections(SectionType sectionType) {
        return sections.get(sectionType);
    }
}
