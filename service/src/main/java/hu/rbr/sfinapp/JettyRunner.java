package hu.rbr.sfinapp;

import hu.rbr.sfinapp.core.config.Config;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.ProtectionDomain;

public class JettyRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Config config;

    private Server server;
    private WebAppContext webAppContext;

    public JettyRunner(Config config) {
        this.config = config;
    }

    public void run() throws Exception {
        log.info("Starting jetty");

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(5);

        server = new Server(threadPool);

        ServerConnector http = new ServerConnector(server);
        http.setPort(config.getInt("http.port"));
        server.addConnector(http);

        webAppContext = new WebAppContext();
        webAppContext.setContextPath(config.get("http.context"));

        ProtectionDomain protectionDomain = App.class.getProtectionDomain();
        String warFile = protectionDomain.getCodeSource().getLocation().toExternalForm();
        log.debug("Using war file: {}", warFile);
        webAppContext.setWar(warFile);

        // A WebAppContext is a ContextHandler as well so it needs to be set to the server so it is aware of where to send the appropriate requests.
        server.setHandler(webAppContext);

        // Start things up!
        server.start();
    }

    public void stop() {
        log.info("Stopping jetty");

        try {
            webAppContext.stop();
            server.stop();
        } catch (Exception e) {
            log.error("Jetty shutdown error.");
        }
    }
}
