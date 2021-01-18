package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {

    private List<User> listOfUsers;

    public ListOfUsersServlet(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> usersData = new HashMap<>();

        if (listOfUsers.size() > 0) {
            usersData.put("userLists", listOfUsers);
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", usersData));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}