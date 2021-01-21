package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.UsersTableDataBase;
import orlovskyi.entity.User;

import java.io.IOException;
import java.util.List;

public class RemoveUsersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersTableDataBase userTable = new UsersTableDataBase();
        List<User> userList = userTable.selectAllUsersFromDataBase();
        long userId = Long.parseLong(req.getParameter("delUserId"));
        for (User users : userList) {
            if (userId == users.getUserId()) {
                userTable.removeUserFromDataBase(users);
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
