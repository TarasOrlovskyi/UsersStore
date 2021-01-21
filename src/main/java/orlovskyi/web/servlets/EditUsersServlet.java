package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import orlovskyi.database.UsersTableDataBase;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditUsersServlet extends HttpServlet {

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
        long editUserId = Long.parseLong(req.getParameter("editUserId"));
        UsersTableDataBase usersTable = new UsersTableDataBase();
        List<User> allUsers = usersTable.selectAllUsersFromDataBase();
        for (User user : allUsers) {
            if (editUserId == user.getUserId()) {
                editUserInList(req, user);
                usersTable.editUserIntoDataBase(user);
                break;
            }
        }
        String searchUsers = req.getParameter("searchUsers");
        if (searchUsers == null) {
            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/users/search?searchUsers="+searchUsers);
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

    private void editUserInList(HttpServletRequest request, User user) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setBirth(birth);
    }
}
