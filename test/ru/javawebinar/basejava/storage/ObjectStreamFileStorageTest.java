package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.ObjectStreamSerializer;

class ObjectStreamFileStorageTest extends AbstractStorageTest{

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}