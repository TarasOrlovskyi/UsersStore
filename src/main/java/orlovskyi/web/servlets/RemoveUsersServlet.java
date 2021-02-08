package orlovskyi.web.servlets;

import orlovskyi.ServiceLocator;
import orlovskyi.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUsersServlet extends HttpServlet {
    private UserService userService = (UserService) ServiceLocator.getService(UserService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.removeUser(Long.parseLong(request.getParameter("delUserId")));
        String searchUsers = request.getParameter("searchUsers");
        if (searchUsers == null) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/users/search?searchUsers="+searchUsers);
        }
    }
}
