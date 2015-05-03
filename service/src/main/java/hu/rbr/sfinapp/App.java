package hu.rbr.sfinapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class App {

    public static final Injector INJECTOR = Guice.createInjector(new SfinappModule());

    public static void main(String[] args) throws Exception {
        App app = INJECTOR.getInstance(App.class);
        app.run();
    }


    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FlywayRunner flywayRunner;
    private final JettyRunner jettyRunner;

    @Inject
    public App(FlywayRunner flywayRunner, JettyRunner jettyRunner) {
        this.flywayRunner = flywayRunner;
        this.jettyRunner = jettyRunner;
    }

    public void run() throws Exception {
        log.info("Starting application");

        addShutdownHook();

        flywayRunner.run();
        jettyRunner.run();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Shutting down application");
                jettyRunner.stop();
            }
        });
    }

}
