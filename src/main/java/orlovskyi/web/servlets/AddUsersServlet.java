package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.UsersTableDataBase;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;

public class AddUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html"));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersTableDataBase usersTable = new UsersTableDataBase();
        User user = createUser(req, usersTable);
        usersTable.addUserToDataBase(user);
        resp.setContentType("text/html;charset=UTF-8");
        resp.sendRedirect("/users");
    }

    private User createUser(HttpServletRequest request, UsersTableDataBase usersTable) {
        User user = new User();
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        user.setUserId(usersTable.getNextUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setBirth(birth);
        return user;
    }
}
