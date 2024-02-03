package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int countResumes;
    private int indexResume;

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public void save(Resume resume) {
        if (countResumes == storage.length) {
            System.out.println("база переполнена");
            return;
        }
        if (isNotExists(resume.getUuid())) {
            storage[countResumes] = resume;
            countResumes++;
            return;
        }
        System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
    }

    public void update(Resume resume) {
        if (isExists(resume.getUuid())) {
            storage[indexResume] = resume;
        }
    }

    public Resume get(String uuid) {
        if (isExists(uuid)) {
            return storage[indexResume];
        }
        return null;
    }

    public void delete(String uuid) {
        if (isExists(uuid)) {
            storage[indexResume] = storage[--countResumes];
            storage[countResumes] = null;
        }
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

    private boolean isExists(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                indexResume = i;
                return true;
            }
        }
        System.out.println("ERROR, резюме с номером " + uuid + " отсутствует");
        return false;
    }

    private boolean isNotExists(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return false;
            }
        }
        return true;
    }
}
