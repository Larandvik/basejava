package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

}
