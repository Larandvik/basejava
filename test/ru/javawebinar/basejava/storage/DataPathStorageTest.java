package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.DataStreamSerializer;

class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}