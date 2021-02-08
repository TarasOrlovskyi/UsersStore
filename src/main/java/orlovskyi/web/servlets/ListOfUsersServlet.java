package orlovskyi.web.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.ServiceLocator;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {

//    private UserService userService;

//    public ListOfUsersServlet(UserService userService){
//        this.userService = userService;
//    }
//    public ListOfUsersServlet(){
//        UserService userService = (UserService) ServiceLocator.getService(UserService.class);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> usersData = new HashMap<>();
        UserService userService = (UserService) ServiceLocator.getService(UserService.class);//new DefaultUserService();
        List<User> listOfUsers = userService.selectAllUsers();
        if (listOfUsers.size() > 0) {
            usersData.put("userLists", listOfUsers);
        }
        response.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", usersData));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}