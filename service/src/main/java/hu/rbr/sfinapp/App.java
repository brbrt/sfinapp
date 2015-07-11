package hu.rbr.sfinapp;

import hu.rbr.sfinapp.core.guice.GuiceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private final FlywayRunner flywayRunner;
    private final ServerRunner serverRunner;

    public static void main(String[] args) throws Exception {
        log.info("Starting application...");

        App app = GuiceHolder.getInjector().getInstance(App.class);
        app.run();
    }

    @Inject
    public App(FlywayRunner flywayRunner, ServerRunner serverRunner) {
        this.flywayRunner = flywayRunner;
        this.serverRunner = serverRunner;
    }

    public void run() throws Exception {
        addShutdownHook();

        flywayRunner.run();
        serverRunner.run();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Stopping application...");
                serverRunner.stop();
                log.info("Application stopped.");
            }
        });
    }

}
