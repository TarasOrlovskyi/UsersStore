package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import orlovskyi.main.Users;

import java.io.IOException;
import java.util.List;

public class RemoveUsersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        List<Object> listOfUsers = (List<Object>) ses.getAttribute("listOfUsers");

        long userId = Long.valueOf(req.getParameter("delUserId"));
        for (int i = 0; i < listOfUsers.size(); i++) {
            Users users = (Users) listOfUsers.get(i);
            if (userId == users.getUserId()) {
                listOfUsers.remove(i);
                break;
            }
        }

        String searchUsers = req.getParameter("searchUsers");
        if (searchUsers == null) {
            resp.sendRedirect("/users");
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("searchUsers", searchUsers);
            resp.sendRedirect("/users/search");
        }
    }
}
