package com.example.controllers;


import com.example.spi.FileStorage;
import com.example.spi.FileStorageFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 100000)
public class FileUploadServlet extends HttpServlet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private FileStorageFactory fileStorageFactory;
    private FileStorage fileStorage;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        FileStorageFactory.getFileStorage();
//    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        // получение содержимого файла из запроса
        String fileName = filePart.getSubmittedFileName();
        long fileSize = filePart.getSize();

        if (fileSize > 100000 || fileName.endsWith(".txt") ||
                fileName.endsWith(".csv")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            // проверка размера и расширения файла
            return;
        }

        String fileId = fileStorage.saveFile(filePart.getInputStream(),
                fileName); // сохранение файла в хранилище
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"id\": \"" + fileId + "\", \"size\": \"" +
                fileSize / 1024 + "\", \"name\": \"" + fileName + "\"}");
    }
}
