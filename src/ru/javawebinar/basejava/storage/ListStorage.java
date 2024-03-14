package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected ArrayList<Resume> listStorage = new ArrayList<>();

    @Override
    protected void saveResume(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return listStorage.get(index);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return listStorage.indexOf(new Resume(uuid));
    }

    @Override
    protected void deleteResume(int index) {
        listStorage.remove(index);
    }
}
