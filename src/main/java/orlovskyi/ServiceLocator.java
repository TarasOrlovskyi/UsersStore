package orlovskyi;

import orlovskyi.dao.UserDao;
import orlovskyi.dao.jdbc.JdbcUserDao;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;
import orlovskyi.web.servlets.*;

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
//        ListOfUsersServlet listOfUsersServlet = new ListOfUsersServlet();
//        SERVICE_MAP.put(ListOfUsersServlet.class, listOfUsersServlet);
//        AddUsersServlet addUsersServlet = new AddUsersServlet(userService);
//        SERVICE_MAP.put(AddUsersServlet.class, addUsersServlet);
//        EditUsersServlet editUsersServlet = new EditUsersServlet(userService);
//        SERVICE_MAP.put(EditUsersServlet.class, editUsersServlet);
//        RemoveUsersServlet removeUsersServlet = new RemoveUsersServlet(userService);
//        SERVICE_MAP.put(RemoveUsersServlet.class, removeUsersServlet);
//        SearchUsersServlet searchUsersServlet = new SearchUsersServlet(userService);
//        SERVICE_MAP.put(SearchUsersServlet.class, searchUsersServlet);
    }

    public static Object getService(Class className){
        return SERVICE_MAP.get(className);
    }
}