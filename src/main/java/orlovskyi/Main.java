package orlovskyi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import orlovskyi.web.servlets.*;


public class Main {
    public static void main(String[] args) throws Exception {

        ListOfUsersServlet listOfUsersServlet = new ListOfUsersServlet();
        AddUsersServlet addUsersServlet = new AddUsersServlet();
        EditUsersServlet editUsersServlet = new EditUsersServlet();
        RemoveUsersServlet removeUsersServlet = new RemoveUsersServlet();
        SearchUsersServlet searchUsersServlet = new SearchUsersServlet();

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
