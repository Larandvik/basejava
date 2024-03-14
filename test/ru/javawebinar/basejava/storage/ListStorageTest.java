package ru.javawebinar.basejava.storage;

class ListStorageTest extends AbstractArrayStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    void saveOverflow() {
        assert true;
    }
}