package orlovskyi.web.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;

import java.io.IOException;

public class RemoveUsersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = new DefaultUserService();
        userService.removeUser(Long.parseLong(request.getParameter("delUserId")));
        String searchUsers = request.getParameter("searchUsers");
        if (searchUsers == null) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/users/search?searchUsers="+searchUsers);
        }
    }
}
