package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("E:\\IDEA_Project\\basejava\\storageTest");

    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";
    private static final String UUID_NOT_EXIST = "uuid not exist";

    private static final String NAME_1 = "A";
    private static final String NAME_2 = "AB";
    private static final String NAME_3 = "Petr Petrov";
    private static final String NAME_4 = "Petr Petrov";

    private static final Resume RESUME_1 = ResumeTestData.getResumeTest(UUID_1, NAME_1);
    private static final Resume RESUME_2 = ResumeTestData.getResumeTest(UUID_2, NAME_2);
    private static final Resume RESUME_3 = ResumeTestData.getResumeTest(UUID_3, NAME_3);
    private static final Resume RESUME_4 = ResumeTestData.getResumeTest(UUID_4, NAME_4);
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertAll(
                () -> assertSize(4),
                () -> assertGet(RESUME_4)
        );
    }

    @Test
    public void saveExisting() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1, NAME_1)));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertAll(
                () -> assertSize(2),
                () -> assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_1))
        );
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_NOT_EXIST));
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, storage.getAllSorted().toArray(new Resume[0]));
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();

        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}