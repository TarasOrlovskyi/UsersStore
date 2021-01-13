package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> mapHTTP = createMapFormRequest(req);

        resp.getWriter().println(PageGenerator.getInstance().getPage("EditUsers.html", mapHTTP));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private static Map<String, Object> createMapFormRequest(HttpServletRequest request){
        Map<String, Object> mapRequest = new HashMap<>();
        mapRequest.put("editUserId", request.getParameter("editUserId"));
        mapRequest.put("editUserFirstName", request.getParameter("editUserFirstName"));
        mapRequest.put("editUserLastName", request.getParameter("editUserLastName"));
        //Integer sal = Integer.parseInt(request.getParameter("editUserSalary"));
        mapRequest.put("editUserSalary", request.getParameter("editUserSalary"));
        mapRequest.put("editUserBirth", request.getParameter("editUserBirth"));
        return mapRequest;
    }
}
