package orlovskyi.web.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;
import orlovskyi.service.impl.DefaultUserService;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;

public class AddUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = new DefaultUserService();
        userService.addUser(createUser(request));
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("/users");
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setSalary(Double.parseDouble(request.getParameter("salary")));
        user.setBirth(LocalDate.parse(request.getParameter("birth")));
        return user;
    }
}
