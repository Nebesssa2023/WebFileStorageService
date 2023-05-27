package com.example.spi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileSystemStorage implements FileStorage{
    private static final String STORAGE_DIRECTORY = "C:\\Users\\Zver\\Desktop\\Prompt";

    @Override
    public String saveFile(InputStream inputStream, String filename) {
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get(STORAGE_DIRECTORY, fileId);
        try {
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
        return fileId;
    }

    public InputStream getFile(String fileId) {
        Path filePath = Paths.get(STORAGE_DIRECTORY, fileId);
        try {
            return Files.newInputStream(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    public List<String> getFileList() {
        try {
            return Files.list(Paths.get(STORAGE_DIRECTORY))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to list files", e);
        }
    }
}
