package ru.javawebinar.basejava.sql;

import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeExtractor implements ResultSetExtractor<List<Resume>> {
    @Override
    public List<Resume> extractData(ResultSet rs) throws SQLException {
        Map<String, Resume> map = new HashMap<>();
        while (rs.next()) {
            String uuid = rs.getString("uuid");
            Resume resume = map.computeIfAbsent(uuid, id -> {
                try {
                    return new Resume(uuid, rs.getString("full_name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            String contactType = rs.getString("type");
            String contactValue = rs.getString("value");
            if (contactType != null && contactValue != null) {
                resume.addContact(ContactType.valueOf(contactType), contactValue);
            }
        }
        return new ArrayList<>(map.values());
    }
}
