package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        List<Object> listOfUsers = (List<Object>) ses.getAttribute("listOfUsers");

        Map<String, Object> usersData = new HashMap<>();

        if (listOfUsers != null) {
            if (listOfUsers.size() > 0) {
                usersData.put("userLists", listOfUsers);
            }
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", usersData));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}