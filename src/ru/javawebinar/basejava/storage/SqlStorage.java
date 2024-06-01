package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    public SqlStorage() {
    }

    @Override
    public void clear() {
        SqlHelper.execute("""
                DELETE
                FROM resume
                """);
    }

    @Override
    public void update(Resume resume) {
        SqlHelper.execute("""
                        UPDATE resume
                        SET full_name = ?
                        WHERE uuid = ?
                        """,
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
        SqlHelper.<Void>execute("""
                        INSERT INTO resume (uuid, full_name)
                        VALUES(?,?)
                            """,
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        return SqlHelper.execute("""
                        SELECT *
                        FROM resume r
                        WHERE r.uuid = ?
                        """,
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
        SqlHelper.execute("""
                        DELETE
                        FROM resume
                        WHERE uuid = ?
                                                """,
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
        return SqlHelper.execute("""
                SELECT *
                FROM resume
                ORDER BY full_name, uuid
                """, ps -> {
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
        return SqlHelper.execute("""
                SELECT count(uuid)
                FROM resume
                """, ps -> {
                    ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt("count") : 0;
                }
        );
    }
}
