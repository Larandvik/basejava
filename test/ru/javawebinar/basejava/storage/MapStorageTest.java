package ru.javawebinar.basejava.storage;

class MapStorageTest extends AbstractArrayStorageTest{

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    void saveOverflow() {
        assert true;
    }
}