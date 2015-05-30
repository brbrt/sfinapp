package hu.rbr.sfinapp;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

public class FlywayRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DataSource dataSource;

    @Inject
    public FlywayRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void run() {
        log.info("Starting flyway.");

        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(dataSource);

        // Start the migration
        flyway.migrate();
    }
}
