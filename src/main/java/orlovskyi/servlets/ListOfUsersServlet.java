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
    Integer i=1;
    List<Object> listOfUsers = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> mapHTTP = new HashMap<>();

        if (listOfUsers.size()>0) {
            mapHTTP.put("userLists", listOfUsers);
        }

//        mapHTTP.put("message", "");
        //mapHTTP.put("userLists", "hello");

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", mapHTTP));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Object> userList = createUserList(req);
        String delUser = req.getParameter("delUser");
        if (delUser != null){
            for(int i=0; i<listOfUsers.size(); i++){
                userStore = (UserStore) listOfUsers.get(i);
                if (delUser.equals(userStore.getUserId())){
                    listOfUsers.remove(i);
                    break;
                }
                //listOfUsers.remove(listOfUsers.indexOf(delUser));
            }
            //listOfUsers.remove(Integer.parseInt(delUser));
        } else {
            createUserList(req);
        }
//        String str;
//
//        for (int i = 0; i < userList.size(); i++) {
//            userStore = (UserStore) userList.get(i);
//            str = userStore.getFirstName();
//        }

        //Map<String, Object> mapHTTP = createMapFromRequest(req);

        Map<String, Object> mapHTTP = new HashMap<>();
        //mapHTTP.put(String.valueOf(i++), userList.get(userList.size()-1));
        mapHTTP.put("userLists", listOfUsers);

        String message = req.getParameter("message");

        resp.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        mapHTTP.put("message", message==null ? "" : message);

        resp.getWriter().println(PageGenerator.getInstance().getPage("ListOfUsers.html", mapHTTP));
    }

    private List<Object> createUserList(HttpServletRequest request){
        //List<Object> listOfUsers = new LinkedList<>();
        //User Id: <input type="number" name="user_id"/>
        //String userId = request.getParameter("user_id");
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

//    private static Map<String, Object> createMapFromRequest(HttpServletRequest request){
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("method", request.getMethod());
//        requestMap.put("URL", request.getRequestURL().toString());
//        requestMap.put("pathInfo", request.getPathInfo());
//        requestMap.put("sessionId", request.getSession().getId());
//        requestMap.put("parameters", request.getParameterMap().toString());
//        return requestMap;
//    }
}
