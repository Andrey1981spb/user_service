package ru.spb.dreamwhite.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("User " + uuid + " already exist", uuid);
    }
}
