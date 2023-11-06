package com.epam.training.olha_haichenkova.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHandler {

    private static final String PATH_TO_CONFIG_FILE = "src//main//resources//config.properties";

    public static void setPropertiesFromConfigFile(){
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(PATH_TO_CONFIG_FILE);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("Failed to read config.properties file: " + e.getLocalizedMessage());
        }
        System.setProperty("browser", properties.getProperty("browser"));
    }

}
