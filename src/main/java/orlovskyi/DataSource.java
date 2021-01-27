package orlovskyi;

import org.postgresql.ds.PGSimpleDataSource;

public class DataSource {
    public static PGSimpleDataSource getDataSource(){
        PropertyReader propertyReader = new PropertyReader();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(propertyReader.getJdbcUser());
        dataSource.setPassword(propertyReader.getJdbcPassword());
        dataSource.setUrl(propertyReader.getJdbcUrl());
        return dataSource;
    }
}
