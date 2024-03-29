package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResumeArray(Resume resume, Object index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResumeArray(Object index) {
        storage[(int) index] = storage[size - 1];
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int key = (int) getSearchKey((String) searchKey);
        return key >= 0;
    }
}
