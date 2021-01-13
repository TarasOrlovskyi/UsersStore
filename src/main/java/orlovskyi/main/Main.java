package orlovskyi.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import orlovskyi.servlets.AddUsersServlet;
import orlovskyi.servlets.EditUsersServlet;
import orlovskyi.servlets.ListOfUsersServlet;


public class Main {
    public static void main(String[] args) throws Exception{
        ListOfUsersServlet listOfUsersServlet = new ListOfUsersServlet();
        AddUsersServlet addUsersServlet = new AddUsersServlet();
        EditUsersServlet editUsersServlet = new EditUsersServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(listOfUsersServlet), "/users");
        contextHandler.addServlet(new ServletHolder(addUsersServlet), "/users/add");
        contextHandler.addServlet(new ServletHolder(editUsersServlet), "/users/edit");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}
