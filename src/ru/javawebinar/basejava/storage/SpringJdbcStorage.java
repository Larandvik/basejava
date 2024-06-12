package ru.javawebinar.basejava.storage;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ResumeExtractor;
import ru.javawebinar.basejava.util.SpringJdbcConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpringJdbcStorage implements Storage, SqlStorageTrain {

    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcStorage() {
        super();
        this.jdbcTemplate = SpringJdbcConfig.jdbcTemplate();
    }

    @Override
    public void clear() {
        jdbcTemplate.update(DELETE_ALL);
    }

    @Override
    public void update(Resume resume) {
        int update = jdbcTemplate.update(UPDATE_RESUME, resume.getFullName(), resume.getUuid());
        if (update == 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        deleteContacts(resume);
        insertContact(resume);
    }

    @Override
    public void save(Resume resume) {
        try {
            jdbcTemplate.update(SAVE_RESUME, resume.getUuid(), resume.getFullName());
        } catch (DuplicateKeyException e) {
            throw new ExistStorageException(resume.getUuid());
        }
        insertContact(resume);
    }

    @Override
    public Resume get(String uuid) {
        return Objects.requireNonNull(jdbcTemplate.query(GET_RESUME, new ResumeExtractor(), uuid))
                .stream()
                .findAny()
                .orElseThrow(() -> new NotExistStorageException(uuid));
    }

    @Override
    public void delete(String uuid) {
        int update = jdbcTemplate.update(DELETE_RESUME, uuid);
        if (update == 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        return jdbcTemplate.query(GET_ALL_SORTED, new ResumeExtractor());
    }

    @Override
    public int size() {
        Integer i = jdbcTemplate.queryForObject(GET_SIZE, Integer.class);
        return i == null ? 0 : i;
    }

    private void insertContact(Resume resume) {
        List<Object[]> batchArgs = new ArrayList<>();
        for (Map.Entry<ContactType, String> ct : resume.getContacts().entrySet()) {
            batchArgs.add(new Object[]{resume.getUuid(), ct.getKey().name(), ct.getValue()});
        }
        jdbcTemplate.batchUpdate(SAVE_CONTACT_RESUME, batchArgs);
    }

    private void deleteContacts(Resume resume) {
        jdbcTemplate.update(DELETE_CONTACT_RESUME, resume.getUuid());
    }
}
