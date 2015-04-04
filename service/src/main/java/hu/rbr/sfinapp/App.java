package hu.rbr.sfinapp;

import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.PropertyConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.flywaydb.core.Flyway;

public class App {

    public static void main(String[] args) throws Exception {
        Config config = new PropertyConfig();

        initFlyway(config);
        initJetty(config);
    }

    private static void initFlyway(Config config) {
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(config.get("db.url"), config.get("db.username"), config.get("db.password"));

        // Start the migration
        flyway.migrate();
    }

    private static void initJetty(Config config) throws Exception {
        Server server = new Server(config.getInt("http.port"));

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(config.get("http.context"));
        webapp.setWar("src/main/webapp");

        // A WebAppContext is a ContextHandler as well so it needs to be set to the server so it is aware of where to send the appropriate requests.
        server.setHandler(webapp);


        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and wait until the server is done executing.
        server.join();
    }
}
