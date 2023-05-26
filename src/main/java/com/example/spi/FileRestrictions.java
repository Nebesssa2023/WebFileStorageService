package com.example.spi;

import java.io.File;
import java.util.List;

public interface FileRestrictions {
    boolean isFileValid(File file);
    int getMaxFileSize();
    List<String> getSkippedExtensions();
}
