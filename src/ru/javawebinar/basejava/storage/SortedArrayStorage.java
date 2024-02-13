package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveAndShift(Resume resume, int index) {
        if (index < 0) {
            index = (-index) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        size++;
    }

    @Override
    protected void deleteAndShift(int index) {
        if (index == STORAGE_LIMIT - 1) {
            storage[index] = null;
            size--;
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        if (size == 0) {
            return 0;
        }
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
