package orlovskyi;

import java.io.*;

public class PropertyReader {
    private static final String PROPERTY_PATH = "C:/Users/240417/IdeaProjects/UsersStore/src/main/resources/application.properties";
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcUrl;

    public PropertyReader(){
        readProperties();
    }

    private void readProperties(){
        File file = new File(PROPERTY_PATH);
        String[] data = readFile(file).split("\\r\\n");

        for (String property : data){
            if (property.contains("jdbc.user")){
                String[] param = property.split(" ");
                jdbcUser = param[param.length-1];
            }
            if (property.contains("jdbc.password")){
                String[] param = property.split(" ");
                jdbcPassword = param[param.length-1];
            }
            if (property.contains("jdbc.url")){
                String[] param = property.split(" ");
                jdbcUrl = param[param.length-1];
            }
        }
    }

    public String getJdbcUser(){
        return jdbcUser;
    }

    public String getJdbcPassword(){
        return jdbcPassword;
    }

    public String getJdbcUrl(){
        return jdbcUrl;
    }

    private String readFile(File file){
        byte[] buffer = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        int byteCount = 0;
        try (InputStream inputStream = new FileInputStream(file)){
            while ((byteCount=inputStream.read(buffer))!=-1){
                stringBuilder.append(new String(buffer, 0, byteCount));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
