package hu.rbr.sfinapp;

import com.google.inject.servlet.GuiceFilter;
import hu.rbr.sfinapp.core.api.JerseyApplication;
import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.guice.GuiceListener;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.EnumSet;

public class ServerRunner implements Runnable {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Config config;

    private HttpServer server;

    @Inject
    public ServerRunner(Config config) {
        this.config = config;
    }

    public void run() {
        log.info("Starting server...");

        URI uri = UriBuilder
                .fromUri("http://0.0.0.0/")
                .port(config.getInt("http.port"))
                .path(config.get("http.context"))
                .build();


        // Create and start a new instance of grizzly http server exposing the Jersey application at uri.
        server = GrizzlyHttpServerFactory.createHttpServer(uri, new JerseyApplication());


        final WebappContext context = new WebappContext("Sfinapp context");

        context.addListener(GuiceListener.class);

        // Initialize and register GuiceFilter.
        final FilterRegistration registration = context.addFilter("GuiceFilter", GuiceFilter.class);
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), "/*");

        context.deploy(server);

        // run
        try {
            server.start();
            log.info("Server has started.");
            Thread.currentThread().join();
        } catch (Exception e) {
            log.error("Server start error.", e);
        }
    }

    public void stop() {
        try {
            server.shutdownNow();
        } catch (Exception e) {
            log.error("Server stop error.", e);
        }
    }
}
