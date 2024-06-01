package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.AfterAll;
import ru.javawebinar.basejava.util.ConnectionManager;

class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage());
    }

    @AfterAll
    public static void tearDown() {
        ConnectionManager.closePool();
    }
}