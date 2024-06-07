package ru.javawebinar.basejava.storage;

class SpringJdbcStorageTest extends AbstractStorageTest {

    public SpringJdbcStorageTest() {
        super(new SpringJdbcStorage());
    }

}