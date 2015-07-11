package hu.rbr.sfinapp.util;

import hu.rbr.sfinapp.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TestApplication {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int port;
    private final String url;

    private Process process;

    public TestApplication(int port, String url) {
        this.port = port;
        this.url = url;
    }

    public void start() {
        log.info("Starting test application...");

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(
                "java",
                "-Dhttp.port=" + port,
                "-Ddb.url=jdbc:h2:mem:sfinapptest",
                "-cp",
                ".:sfinapp-service-standalone.jar",
                App.class.getName()
        );

        processBuilder.directory(new File("target"));

        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        try {
            process = processBuilder.start();
            ApplicationChecker.waitUntilAvailable(url + "sample");
        } catch (Exception e) {
            throw new RuntimeException("Application start error.", e);
        }

        log.info("Test application started.");
    }

    public void stop() {
        log.info("Stopping test application...");
        process.destroy();
    }

}
