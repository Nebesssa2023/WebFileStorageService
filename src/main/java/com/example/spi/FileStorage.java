package com.example.spi;

import java.util.List;

public interface FileStorage {
    void saveFile(String fileName, byte[] data);
    byte[] getFile(String fileName);
    List<String> getFileList();
}
