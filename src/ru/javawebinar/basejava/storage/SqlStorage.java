package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    protected static final String DELETE_ALL = """
            DELETE
            FROM resume
            """;
    protected static final String UPDATE_RESUME = """
            UPDATE resume
            SET full_name = ?
            WHERE uuid = ?
            """;
    protected static final String SAVE_RESUME = """
            INSERT INTO resume (uuid, full_name)
            VALUES(?,?)
                """;
    protected static final String GET_RESUME = """
            SELECT *
            FROM resume r
            WHERE r.uuid = ?
            """;
    protected static final String DELETE_RESUME = """
            DELETE
            FROM resume
            WHERE uuid = ?
                                    """;
    protected static final String GET_ALL_SORTED = """
            SELECT *
            FROM resume
            ORDER BY full_name, uuid
            """;
    protected static final String GET_SIZE = """
            SELECT count(uuid)
            FROM resume
            """;

    public SqlStorage() {
    }

    @Override
    public void clear() {
        SqlHelper.execute(DELETE_ALL);
    }

    @Override
    public void update(Resume resume) {
        SqlHelper.execute(UPDATE_RESUME,
                ps -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    int rowCount = ps.executeUpdate();
                    if (rowCount == 0) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
                    return null;
                });
    }

    @Override
    public void save(Resume resume) {
        SqlHelper.<Void>execute(SAVE_RESUME,
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
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
                    return new Resume(uuid, rs.getString("full_name"));
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
