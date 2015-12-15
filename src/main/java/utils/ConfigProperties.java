package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            PROPERTIES.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getConfigProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

