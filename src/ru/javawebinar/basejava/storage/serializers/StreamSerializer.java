package ru.javawebinar.basejava.storage.serializers;

import ru.javawebinar.basejava.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {

    void doWrite(Resume resume, OutputStream os);

    Resume doRead(InputStream is);
}