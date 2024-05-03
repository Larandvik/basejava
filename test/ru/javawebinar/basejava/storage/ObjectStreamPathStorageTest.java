package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.ObjectStreamSerializer;

class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}