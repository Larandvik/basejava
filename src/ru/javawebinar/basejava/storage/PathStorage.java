package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializers.StreamSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer serializer;

    protected PathStorage(String dir, StreamSerializer serializer) {
        Objects.requireNonNull(serializer, "serializer must not be null");
        Objects.requireNonNull(dir, "directory must not be null");

        this.serializer = serializer;
        directory = Paths.get(dir);

        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list;
        try (Stream<Path> stream = Files.list(directory)) {
            list = stream.map(this::getResume)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new StorageException("IO error", directory.getFileName().toString(), e);
        }
        return list;
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serializer.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            serializer.doWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", path.getFileName().toString());
        }
        updateResume(resume, path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.getFileName().toString(), uuid);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("not deleted", path.getFileName().toString());
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try (Stream<Path> stream = Files.list(directory)) {
            return (int) stream.count();
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }
}
