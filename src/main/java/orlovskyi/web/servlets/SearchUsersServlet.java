package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchUsersServlet extends HttpServlet {
    private List<User> listOfUsers;

    public SearchUsersServlet(List<User> listOfUsers){
        this.listOfUsers = listOfUsers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Object> listOfSearchedUsers = new LinkedList<>();
        Map<String, Object> usersData = new HashMap<>();
        String searchUsers = req.getParameter("searchUsers");

//        if (searchUsers == null) {
//            searchUsers = (String) ses.getAttribute("searchUsers");
//        }

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
