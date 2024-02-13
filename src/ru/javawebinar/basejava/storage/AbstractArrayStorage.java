package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("база переполнена");
            return;
        } else if (index > 0) {
            System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
            return;
        }
        saveResume(resume, index);
        size++;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printIsNotExist(uuid);
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            printIsNotExist(uuid);
            return;
        }
        deleteResume(index);
        size--;
        storage[size] = null;
    }

    @Override
    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.print("Update: ");
            printIsNotExist(resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);

    protected abstract void deleteResume(int index);

    protected void printIsNotExist(String uuid) {
        System.out.println("резюме с номером " + uuid + " не существует");
    }
}
