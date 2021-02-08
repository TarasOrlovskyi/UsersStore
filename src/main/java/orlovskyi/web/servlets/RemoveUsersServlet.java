package orlovskyi.web.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.ServiceLocator;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;

import java.io.IOException;

public class RemoveUsersServlet extends HttpServlet {

//    private UserService userService;
//
//    public RemoveUsersServlet(UserService userService){
//        this.userService = userService;
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) ServiceLocator.getService(UserService.class);//new DefaultUserService();
        userService.removeUser(Long.parseLong(request.getParameter("delUserId")));
        String searchUsers = request.getParameter("searchUsers");
        if (searchUsers == null) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/users/search?searchUsers="+searchUsers);
        }
    }
}
