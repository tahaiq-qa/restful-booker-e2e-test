package com.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // get baseurl
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
