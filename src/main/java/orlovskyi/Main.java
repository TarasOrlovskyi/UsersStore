package orlovskyi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import orlovskyi.entity.User;
import orlovskyi.web.servlets.*;

import java.util.LinkedList;
import java.util.List;


public class Main {
    private static List<User> listOfUsers = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        ListOfUsersServlet listOfUsersServlet = new ListOfUsersServlet(listOfUsers);
        AddUsersServlet addUsersServlet = new AddUsersServlet(listOfUsers);
        EditUsersServlet editUsersServlet = new EditUsersServlet(listOfUsers);
        RemoveUsersServlet removeUsersServlet = new RemoveUsersServlet(listOfUsers);
        SearchUsersServlet searchUsersServlet = new SearchUsersServlet(listOfUsers);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(listOfUsersServlet), "/users");
        contextHandler.addServlet(new ServletHolder(addUsersServlet), "/users/add");
        contextHandler.addServlet(new ServletHolder(editUsersServlet), "/users/edit");
        contextHandler.addServlet(new ServletHolder(removeUsersServlet), "/users/remove");
        contextHandler.addServlet(new ServletHolder(searchUsersServlet), "/users/search");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}
