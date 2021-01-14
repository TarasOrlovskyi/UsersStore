package orlovskyi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orlovskyi.main.UserStore;
import orlovskyi.templator.PageGenerator;

import java.io.IOException;
import java.util.*;

public class ListOfUsersServlet extends HttpServlet {
    private UserStore userStore;
    Integer i = 1;
    List<Object> listOfUsers = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> mapHTTP = new HashMap<>();
        String searchUsers = req.getParameter("searchUsers");
        if (listOfUsers.size() > 0) {
            mapHTTP.put("userLists", listOfUsers);
        }
        if (searchUsers != null) {
            mapHTTP.put("searchUsers", searchUsers);
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", mapHTTP));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delUser = req.getParameter("delUser");
        String editUser = req.getParameter("editUserId");
        if (delUser != null) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                userStore = (UserStore) listOfUsers.get(i);
                if (delUser.equals(userStore.getUserId())) {
                    listOfUsers.remove(i);
                    break;
                }
            }
        } else if (editUser != null) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                userStore = (UserStore) listOfUsers.get(i);
                if (editUser.equals(userStore.getUserId())) {
                    editUserInList(req);
                }
            }
        } else {
            createUserList(req);
        }

        Map<String, Object> mapHTTP = new HashMap<>();
        if (listOfUsers.size() > 0) {
            mapHTTP.put("userLists", listOfUsers);
        }

        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", mapHTTP));
    }

    private List<Object> createUserList(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        int salary = Integer.parseInt(request.getParameter("salary"));
//        int year = Integer.parseInt(request.getParameter("year"));
//        int month = Integer.parseInt(request.getParameter("month"));
//        int day = Integer.parseInt(request.getParameter("day"));
        String birth = request.getParameter("birth");
        //Integer userID = listOfUsers.size();
        //Integer.parseInt(userId);
        //listOfUsers.add(new UserStore(userID.toString(), firstName, lastName, salary, birth));
        listOfUsers.add(new UserStore(i.toString(), firstName, lastName, salary, birth));
        i++;
        return listOfUsers;
    }

    private void editUserInList(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        int salary = Integer.parseInt(request.getParameter("salary"));
        String birth = request.getParameter("birth");
        userStore.setFirstName(firstName);
        userStore.setLastName(lastName);
        userStore.setSalary(salary);
        userStore.setBirth(birth);
    }
}
