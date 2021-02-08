package orlovskyi.web.servlets;

import orlovskyi.ServiceLocator;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;
import orlovskyi.web.templator.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {
    private UserService userService = (UserService) ServiceLocator.getService(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> usersData = new HashMap<>();
        List<User> listOfUsers = userService.selectAllUsers();
        if (listOfUsers.size() > 0) {
            usersData.put("userLists", listOfUsers);
        }
        response.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", usersData));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}