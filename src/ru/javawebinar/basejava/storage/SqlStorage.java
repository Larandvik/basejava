package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage, SqlStorageTrain {

    public SqlStorage() {
    }

    @Override
    public void clear() {
        SqlHelper.execute(DELETE_ALL);
    }

    @Override
    public void update(Resume resume) {
        SqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_RESUME)) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                int rowCount = ps.executeUpdate();
                if (rowCount == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(resume);
            insertContact(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        SqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement(SAVE_RESUME)) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContact(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return SqlHelper.execute(GET_RESUME,
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                    } while (rs.next());

                    return resume;
                });
    }

    @Override
    public void delete(String uuid) {
        SqlHelper.execute(DELETE_RESUME,
                ps -> {
                    ps.setString(1, uuid);
                    int rowCount = ps.executeUpdate();
                    if (rowCount == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return SqlHelper.execute(GET_ALL_SORTED, ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> map = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = map.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, rs.getString("full_name"));
                    map.put(uuid, resume);
                }
                addContact(rs, resume);
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return SqlHelper.execute(GET_SIZE, ps -> {
                    ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt("count") : 0;
                }
        );
    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SAVE_CONTACT_RESUME)) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Resume r) {
        SqlHelper.execute(DELETE_CONTACT_RESUME, ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }
}
