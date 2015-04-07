package hu.rbr.sfinapp;

import hu.rbr.sfinapp.core.config.Config;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlywayRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Config config;

    public FlywayRunner(Config config) {
        this.config = config;
    }

    public void run() {
        log.info("Starting flyway.");

        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(config.get("db.url"), config.get("db.username"), config.get("db.password"));

        // Start the migration
        flyway.migrate();
    }
}
