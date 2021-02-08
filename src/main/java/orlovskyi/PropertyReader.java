package orlovskyi;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null){
                throw new RuntimeException("File 'application.properties' not found!");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error during load properties!", e);
        }
        return properties;
    }
}
