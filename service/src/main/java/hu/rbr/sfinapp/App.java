package hu.rbr.sfinapp;

import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private final Logger log = LoggerFactory.getLogger(App.class);

    private JettyRunner jettyRunner;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() throws Exception {
        log.info("Starting application");

        addShutdownHook();

        Config config = new PropertyConfig();

        new FlywayRunner(config).run();

        jettyRunner = new JettyRunner(config);
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
