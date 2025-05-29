package org.chypakk;

import com.sun.net.httpserver.HttpServer;
import org.chypakk.handler.WeatherHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WeatherServer {
    public static void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/weather", new WeatherHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }
}
