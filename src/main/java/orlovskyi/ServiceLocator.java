package orlovskyi;

import orlovskyi.dao.UserDao;
import orlovskyi.dao.jdbc.JdbcUserDao;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class, Object> SERVICE_MAP = new HashMap<>();

    static {
        PropertyReader propertyReader = new PropertyReader();
        DataSource dataSource = new DataSource(propertyReader.readProperties());
        UserDao userDao = new JdbcUserDao(dataSource);
        UserService userService = new DefaultUserService(userDao);
        SERVICE_MAP.put(UserService.class, userService);
    }

    public static Object getService(Class className){
        return SERVICE_MAP.get(className);
    }
}