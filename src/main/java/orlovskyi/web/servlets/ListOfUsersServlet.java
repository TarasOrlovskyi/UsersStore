package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.database.UsersTableDataBase;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> usersData = new HashMap<>();
        UsersTableDataBase usersTable = new UsersTableDataBase();
        List<User> listOfUsers = usersTable.selectAllUsersFromDataBase();
        if (listOfUsers.size() > 0) {
            usersData.put("userLists", listOfUsers);
        }
        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", usersData));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}