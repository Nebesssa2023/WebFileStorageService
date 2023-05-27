package com.example.jetty;

import com.example.controllers.DownloadsServlet;
import com.example.controllers.FileUploadServlet;
import com.example.controllers.FilesServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new DownloadsServlet()),"/download/*");
        context.addServlet(new ServletHolder(new FilesServlet()), "/files");
        context.addServlet(new ServletHolder(new FileUploadServlet()),"/upload");
        server.start();
        server.join();
    }
}
