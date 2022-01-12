package com.solvd.testingpractice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil implements iCharsKeeper {

    private final static Logger LOGGER = LogManager.getLogger(ConfigUtil.class);
    private static final Properties properties = new Properties();
    private final static String propertiesPath = "src/main/resources/config.properties";

    static {
        FileInputStream input = null;
        try {
            input = new FileInputStream(propertiesPath);
            properties.load(input);
        } catch (IOException e) {
            LOGGER.warn("Failed to load properties!", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.warn(e.getStackTrace());
                }
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
