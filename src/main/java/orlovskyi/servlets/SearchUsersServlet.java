package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import orlovskyi.main.Users;
import orlovskyi.templator.PageGenerator;

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
        String searchUsers;

        HttpSession ses = req.getSession();
        List<Object> listOfUsers = (List<Object>) ses.getAttribute("listOfUsers");

        searchUsers = req.getParameter("searchUsers");
        if (searchUsers == null) {
            searchUsers = (String) ses.getAttribute("searchUsers");
        }

        for (int i = 0; i < listOfUsers.size(); i++) {
            Users users = (Users) listOfUsers.get(i);
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
