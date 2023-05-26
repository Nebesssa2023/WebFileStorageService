package com.example.spi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemStorage implements FileStorage, FileRestrictions{
    private final String storagePath;
    private static final int MAX_FILE_SIZE = 100 * 1024;
    private static final List<String> SKIPPED_EXTENSIONS = Arrays.asList(".txt", ".csv");

    public FileSystemStorage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void saveFile(String fileName, byte[] data) {
        try {
            if (isFileValid(new File(fileName))) {
                Files.write(Paths.get(storagePath, fileName), data);
            } else {
                System.out.println("The file does not meet the established restrictions!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    public byte[] getFile(String fileName) {
        try {
            return Files.readAllBytes(Paths.get(storagePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    public List<String> getFileList() {
        try {
            return Files.list(Paths.get(storagePath))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to list files", e);
        }
    }

    @Override
    public boolean isFileValid(File file) {
        return file.length() <= getMaxFileSize() && !getSkippedExtensions()
                .contains(getFileExtension(file));    }

    @Override
    public int getMaxFileSize() {
        return MAX_FILE_SIZE;
    }

    @Override
    public List<String> getSkippedExtensions() {
        return SKIPPED_EXTENSIONS;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }
}
