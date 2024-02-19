package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStorageTest extends AbstractArrayStorageTest {

    private final Storage storage = new ArrayStorage();

    public ArrayStorageTest(Storage storage) {
        super(storage);
    }

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void save() {
        storage.save(new Resume());
        assertEquals(4, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void get() {
        Resume resume = new Resume(UUID_1);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test
    void update() {
        Resume expected = new Resume(UUID_1);
        storage.update(expected);
        assertEquals(expected, storage.get(UUID_1));
    }

    @Test
    void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }
}