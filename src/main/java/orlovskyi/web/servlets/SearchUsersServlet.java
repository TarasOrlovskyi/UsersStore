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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUsersServlet extends HttpServlet {

//    private UserService userService;
//
//    public SearchUsersServlet(UserService userService){
//        this.userService = userService;
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> usersData = new HashMap<>();
        UserService userService = (UserService) ServiceLocator.getService(UserService.class);//new DefaultUserService();
        String searchUsers = request.getParameter("searchUsers");
        List<User> listOfSearchedUsers = userService.selectSearchedUsers(searchUsers);
        usersData.put("searchUsers", searchUsers);
        usersData.put("listOfSearchedUsers", listOfSearchedUsers);
        response.getWriter().println(PageGenerator.getInstance().getPage("SearchUsers.html", usersData));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
