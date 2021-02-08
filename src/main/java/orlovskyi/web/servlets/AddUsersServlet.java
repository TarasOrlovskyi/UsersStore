package orlovskyi.web.servlets;

import orlovskyi.ServiceLocator;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;
import orlovskyi.web.templator.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddUsersServlet extends HttpServlet {
    private UserService userService = (UserService) ServiceLocator.getService(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
