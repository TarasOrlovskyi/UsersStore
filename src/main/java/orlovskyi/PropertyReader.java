package orlovskyi;

import java.io.*;
import java.util.Properties;

public class PropertyReader {
    private static final String PROPERTY_PATH = "C:/Users/240417/IdeaProjects/UsersStore/src/main/resources/application.properties";
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcUrl;

    public PropertyReader() {
        readProperties();
    }

    private void readProperties() {
        final Properties properties = new Properties();
        File file = new File(PROPERTY_PATH);
        try (InputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
            jdbcUser = properties.getProperty("jdbc.user");
            jdbcPassword = properties.getProperty("jdbc.password");
            jdbcUrl = properties.getProperty("jdbc.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
