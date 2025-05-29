package org.chypakk;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            WeatherServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}