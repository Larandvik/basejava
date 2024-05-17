package ru.javawebinar.basejava.storage.serializers;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                serializeSection(entry.getValue(), dos);
            }
        }
    }

    private void serializeSection(AbstractSection section, DataOutputStream dos) throws IOException {
        switch (section) {
            case TextSection tx -> {
                dos.writeUTF("TextSection");
                dos.writeUTF(tx.getText());
            }
            case ListSection ls -> {
                dos.writeUTF("ListSection");
                List<String> items = ls.getItems();
                dos.writeInt(items.size());
                for (String item : items) {
                    dos.writeUTF(item);
                }
            }
            case CompanySection cs -> {
                dos.writeUTF("CompanySection");
                List<Company> companies = cs.getCompanies();
                dos.writeInt(companies.size());
                for (Company company : companies) {
                    dos.writeUTF(company.getName());
                    dos.writeUTF(company.getWebsite());
                    List<Period> periods = company.getPeriods();
                    dos.writeInt(periods.size());
                    for (Period period : periods) {
                        dos.writeInt(period.getStartDate().getYear());
                        dos.writeUTF(period.getStartDate().getMonth().name());
                        dos.writeInt(period.getEndDate().getYear());
                        dos.writeUTF(period.getEndDate().getMonth().name());
                        dos.writeUTF(period.getTitle());
                        dos.writeUTF(period.getDescription());
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + section);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                AbstractSection section = deserializeSection(dis, type);
                resume.addSection(type, section);
            }
            return resume;
        }
    }

    private AbstractSection deserializeSection(DataInputStream dis, SectionType type) throws IOException {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> {
                int size = dis.readInt();
                List<String> items = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    items.add(dis.readUTF());
                }
                yield new ListSection(items);
            }
            case EXPERIENCE, EDUCATION -> {
                int companyCount = dis.readInt();
                List<Company> companies = new ArrayList<>();
                for (int i = 0; i < companyCount; i++) {
                    String name = dis.readUTF();
                    String website = dis.readUTF();
                    int periodsCount = dis.readInt();
                    List<Period> periods = new ArrayList<>();
                    for (int j = 0; j < periodsCount; j++) {
                        int startYear = dis.readInt();
                        Month startMonth = Month.valueOf(dis.readUTF());
                        int endYear = dis.readInt();
                        Month endMonth = Month.valueOf(dis.readUTF());
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        periods.add(new Period(startYear, startMonth, endYear, endMonth, title, description));
                    }
                    companies.add(new Company(name, website, periods));
                }
                CompanySection companySection = new CompanySection();
                companySection.setCompanies(companies);
                yield companySection;
            }
        };
    }
}

