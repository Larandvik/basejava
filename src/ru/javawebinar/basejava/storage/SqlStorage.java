package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_CONTACT_RESUME)) {
                for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                    ps.setString(2, resume.getUuid());
                    ps.setString(3, e.getKey().name());
                    ps.setString(1, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
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
            try (PreparedStatement ps = conn.prepareStatement(SAVE_CONTACT_RESUME)) {
                for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {


                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
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
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        resume.addContact(type, value);
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
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                resumes.add(r);
            }
            return resumes;
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
}
