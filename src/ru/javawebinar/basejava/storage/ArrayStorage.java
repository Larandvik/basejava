package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("база переполнена");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
            return;
        }
        storage[size] = resume;
        size++;
    }

//    public void update(Resume resume) {
//        int index = getIndex(resume.getUuid());
//        if (isExist(index)) {
//            storage[index] = resume;
//        }
//        printIsNotExist(resume.getUuid());
//    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[--size];
            storage[size] = null;
            return;
        }
        printIsNotExist(uuid);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
