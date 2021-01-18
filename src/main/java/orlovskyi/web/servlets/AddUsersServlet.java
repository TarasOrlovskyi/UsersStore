package orlovskyi.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.entity.User;
import orlovskyi.web.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddUsersServlet extends HttpServlet {
    private long i = 1;
    private List<User> listOfUsers;

    public AddUsersServlet(List<User> listOfUsers){
        this.listOfUsers = listOfUsers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html"));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = createUser(req);
        listOfUsers.add(user);

        resp.setContentType("text/html;charset=UTF-8");

        resp.sendRedirect("/users");
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        user.setUserId(i);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setBirth(birth);
        i++;
        return user;
    }
}
