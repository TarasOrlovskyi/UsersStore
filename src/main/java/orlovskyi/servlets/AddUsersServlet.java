package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import orlovskyi.main.Users;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AddUsersServlet extends HttpServlet {
    private long i = 1;
    private List<Object> listOfUsers = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> userData = new HashMap<>();
        resp.getWriter().println(PageGenerator.getInstance().getPage("AddUsers.html", userData));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createUserList(req);

        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(true);
        session.setAttribute("listOfUsers", listOfUsers);

        resp.sendRedirect("/users");
    }

    private void createUserList(HttpServletRequest request) {
        Users users = new Users();
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        users.setUserId(i);
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setSalary(salary);
        users.setBirth(birth);
        listOfUsers.add(users);
        i++;
    }
}
