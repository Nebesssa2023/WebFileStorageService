package com.example.spi;

import java.util.ServiceLoader;

public class FileStorageFactory {
    private static final String SERVICE_NAME =
            "com.example.FileStorage";

    public static FileStorage getFileStorage() {
        ServiceLoader<FileStorage> loader = ServiceLoader.load(FileStorage.class);
        for (FileStorage storage : loader) {
            if (storage.getClass().getName().equals(SERVICE_NAME)) {
                return storage;
            }
        }
        throw new RuntimeException("File storage not found");
    }
}
