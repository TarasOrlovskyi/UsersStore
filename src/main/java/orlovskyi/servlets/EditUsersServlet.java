package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import orlovskyi.main.Users;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditUsersServlet extends HttpServlet {

    private Users users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> usersData = createMapFormRequest(req);
        usersData.put("searchUsers", req.getParameter("searchUsers"));

        resp.getWriter().println(PageGenerator.getInstance().getPage("EditUsers.html", usersData));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        List<Object> listOfUsers = (List<Object>) ses.getAttribute("listOfUsers");
        long editUserId = Long.valueOf(req.getParameter("editUserId"));
        for (int i = 0; i < listOfUsers.size(); i++) {
            users = (Users) listOfUsers.get(i);
            if (editUserId == users.getUserId()) {
                editUserInList(req);
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

    private static Map<String, Object> createMapFormRequest(HttpServletRequest request) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("editUserId", request.getParameter("editUserId"));
        userData.put("editUserFirstName", request.getParameter("editUserFirstName"));
        userData.put("editUserLastName", request.getParameter("editUserLastName"));
        userData.put("editUserSalary", request.getParameter("editUserSalary"));
        userData.put("editUserBirth", request.getParameter("editUserBirth"));
        return userData;
    }

    private void editUserInList(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setSalary(salary);
        users.setBirth(birth);
    }
}
