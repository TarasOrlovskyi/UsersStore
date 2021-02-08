package orlovskyi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyReaderTest {

    @Test
    void testReadProperties(){
        PropertyReader propertyReader = new PropertyReader();
        assertEquals("postgres", propertyReader.readProperties().getProperty("jdbc.user"));
        assertEquals("ivasyutyak", propertyReader.readProperties().getProperty("jdbc.password"));
        assertEquals("jdbc:postgresql://localhost:5432/usersstore", propertyReader.readProperties().getProperty("jdbc.url"));
    }
}