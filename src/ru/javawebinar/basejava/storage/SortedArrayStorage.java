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
        } else if (index > 0) {
            System.out.println("резюме с номер " + resume.getUuid() + " уже существует");
        } else if (index == -1) {
            storage[size] = resume;
        } else if (index < 0) {
            index = (-index) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        size++;
    }


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            printIsNotExist(uuid);
            return;
        } else if (index == STORAGE_LIMIT - 1) {
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
