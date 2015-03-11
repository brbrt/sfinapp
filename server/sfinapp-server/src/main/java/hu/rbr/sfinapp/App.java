package hu.rbr.sfinapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class App {

    public static void main(String[] args) throws Exception {

        // TODO: parameters from properties
        Server server = new Server(8080);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("src/main/webapp");

        // A WebAppContext is a ContextHandler as well so it needs to be set to the server so it is aware of where to send the appropriate requests.
        server.setHandler(webapp);
        

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and wait until the server is done executing.
        server.join();
    }
}
