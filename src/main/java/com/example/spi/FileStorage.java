package com.example.spi;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileStorage {
    String saveFile(InputStream inputStream, String filename) throws IOException;
    InputStream getFile(String fileId) throws IOException;
    List<String> getFileList();
}
