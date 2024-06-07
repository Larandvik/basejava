package ru.javawebinar.basejava.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResumeMapper implements RowMapper<Resume> {
    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume resume = new Resume();

        resume.setFullName(rs.getString("full_name"));
        resume.setUuid(rs.getString("uuid"));

        return resume;
    }
}
