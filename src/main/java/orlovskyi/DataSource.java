package orlovskyi;

import org.postgresql.ds.PGSimpleDataSource;

import java.util.Properties;

public class DataSource {

    private Properties properties;

    public DataSource(Properties properties){
        this.properties = properties;
    }
    public PGSimpleDataSource getDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(properties.getProperty("jdbc.user"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        return dataSource;
    }
}
