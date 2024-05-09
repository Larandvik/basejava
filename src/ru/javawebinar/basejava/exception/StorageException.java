package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Throwable e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(String message, Throwable e) {
        this(message, null, e);
    }

    public String getUuid() {
        return uuid;
    }
}
