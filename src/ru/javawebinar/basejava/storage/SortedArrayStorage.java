package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("база переполнена");
            return;
        } else if (index == -1) {
            System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
            return;
        }
        storage[size] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printIsNotExist(uuid);
            return;
        } else if (index == STORAGE_LIMIT - 1) {
            storage[index] = null;
            size--;
            return;
        }
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
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
