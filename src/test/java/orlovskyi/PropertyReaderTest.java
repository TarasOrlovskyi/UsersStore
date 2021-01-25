package orlovskyi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyReaderTest {

    @Test
    void testGetJdbcUser(){
        PropertyReader propertyReader = new PropertyReader();
        assertEquals("postgres", propertyReader.getJdbcUser());
    }

    @Test
    void testGetJdbcPassword(){
        PropertyReader propertyReader = new PropertyReader();
        assertEquals("ivasyutyak", propertyReader.getJdbcPassword());
    }

    @Test
    void testGetJdbcUrl(){
        PropertyReader propertyReader = new PropertyReader();
        assertEquals("jdbc:postgresql://localhost:5432/usersstore", propertyReader.getJdbcUrl());
    }
}