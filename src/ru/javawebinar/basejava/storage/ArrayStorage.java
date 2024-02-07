package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int STORAGE_LIMIT = 10000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int countResumes;

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public void save(Resume resume) {
        if (countResumes == STORAGE_LIMIT) {
            System.out.println("база переполнена");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
            return;
        }
        storage[countResumes] = resume;
        countResumes++;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
        }
        printIsNotExist(resume.getUuid());
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        printIsNotExist(uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            storage[index] = storage[--countResumes];
            storage[countResumes] = null;
            return;
        }
        printIsNotExist(uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    public int size() {
        return countResumes;
    }

    private boolean isExist(int index) {
        return index != -1;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printIsNotExist(String uuid) {
        System.out.println("резюме с номером " + uuid + " не существует");
    }
}
