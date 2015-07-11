package hu.rbr.sfinapp;

import hu.rbr.sfinapp.util.TestApplication;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;

public class IntegrationTestBase {

    public static final int PORT = 28282;
    public static final String BASE_URL = "http://localhost:" + PORT + "/api/";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private TestApplication testApplication;

    @Before
    public void init() throws IOException {
        testApplication = new TestApplication(PORT, BASE_URL);
        testApplication.start();
    }

    @After
    public void destroy() {
        testApplication.stop();
    }

    protected WebTarget webTarget(String path) {
        Client client = ClientBuilder.newClient();
        return client.target(BASE_URL).path(path);
    }

}
