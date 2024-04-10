package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

}
