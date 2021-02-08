package orlovskyi.web.servlets;

import orlovskyi.ServiceLocator;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;
import orlovskyi.web.templator.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EditUsersServlet extends HttpServlet {
    private UserService userService = (UserService) ServiceLocator.getService(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> usersData = createMapFormRequest(request);
        usersData.put("searchUsers", request.getParameter("searchUsers"));
        response.getWriter().println(PageGenerator.getInstance().getPage("EditUsers.html", usersData));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long editUserId = Long.parseLong(request.getParameter("editUserId"));
        userService.editUser(editUserInList(request, editUserId));
        String searchUsers = request.getParameter("searchUsers");
        if (searchUsers == null) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/users/search?searchUsers="+searchUsers);
        }
    }

    private static Map<String, Object> createMapFormRequest(HttpServletRequest request) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("editUserId", request.getParameter("editUserId"));
        userData.put("editUserFirstName", request.getParameter("editUserFirstName"));
        userData.put("editUserLastName", request.getParameter("editUserLastName"));
        userData.put("editUserSalary", request.getParameter("editUserSalary"));
        userData.put("editUserBirth", request.getParameter("editUserBirth"));
        return userData;
    }

    private User editUserInList(HttpServletRequest request, long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setSalary(Double.parseDouble(request.getParameter("salary")));
        user.setBirth(LocalDate.parse(request.getParameter("birth")));
        return user;
    }
}
