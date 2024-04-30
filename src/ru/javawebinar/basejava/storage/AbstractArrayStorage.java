package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected final void saveResume(Resume resume, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveResumeArray(resume, searchKey);
        size++;
    }

    @Override
    protected final void deleteResume(Integer index) {
        deleteResumeArray(index);
        size--;
        storage[size] = null;
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public final void updateResume(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResumeArray(Resume resume, Integer index);

    protected abstract void deleteResumeArray(Integer index);

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}
