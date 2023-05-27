package com.example.controllers;

import com.example.spi.FileStorage;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {
    private FileStorage fileStorage;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        var files = fileStorage.getFileList();
        List<Map<String, Object>> filesJson = new ArrayList<>();
        for (String file : files) {
            Map<String, Object> fileJson = new HashMap<>();
            fileJson.put("id", request.getPathInfo().substring(1));
            fileJson.put("size", file.length() / 1024);
            filesJson.add(fileJson);
        }
        String json = new Gson().toJson(filesJson);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
