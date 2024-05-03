package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializers.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final StreamSerializer serializer;


    protected FileStorage(File directory, StreamSerializer serializer) {
        Objects.requireNonNull(serializer, "serializer must not be null");
        Objects.requireNonNull(directory, "directory must not be null");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.serializer = serializer;
        this.directory = directory;
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(getResume(file));
        }
        return list;
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile(); // checked in AbstractStorage
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(resume, file);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void deleteResume(File file) {
        boolean isDelete = file.delete();
        if (!isDelete) {
            throw new StorageException("not deleted", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        File[] files = getCheckedListFiles();
        for (File file : files) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        return getCheckedListFiles().length;
    }

    private File[] getCheckedListFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("IO error", directory.getName());
        }
        return files;
    }
}
