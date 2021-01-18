package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.entity.User;

import java.io.IOException;
import java.util.List;

public class RemoveUsersServlet extends HttpServlet {
    private List<User> listOfUsers;

    public RemoveUsersServlet(List<User> listOfUsers){
        this.listOfUsers = listOfUsers;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long userId = Long.parseLong(req.getParameter("delUserId"));
        for (int i = 0; i < listOfUsers.size(); i++) {
            User users = listOfUsers.get(i);
            if (userId == users.getUserId()) {
                listOfUsers.remove(i);
                break;
            }
        }
        String searchUsers = req.getParameter("searchUsers");
        if (searchUsers == null) {
            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/users/search?searchUsers="+searchUsers);
        }
    }
}
