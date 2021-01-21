package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.UsersTableDataBase;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Object> listOfSearchedUsers = new LinkedList<>();
        Map<String, Object> usersData = new HashMap<>();
        UsersTableDataBase usersTable = new UsersTableDataBase();
        String searchUsers = req.getParameter("searchUsers");
        List<User> listOfUsers = usersTable.selectAllUsersFromDataBase();
        for (User users : listOfUsers) {
            if (users.getFirstName().toLowerCase().contains(searchUsers.toLowerCase()) || users.getLastName().toLowerCase().contains(searchUsers.toLowerCase())) {
                listOfSearchedUsers.add(users);
            }
        }
        usersData.put("searchUsers", searchUsers);
        usersData.put("listOfSearchedUsers", listOfSearchedUsers);
        resp.getWriter().println(PageGenerator.getInstance().getPage("SearchUsers.html", usersData));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
