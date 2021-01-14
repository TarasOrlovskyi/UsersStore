package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> mapHTTP = new HashMap<>();

        resp.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html", mapHTTP));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
