package hu.rbr.sfinapp;

import org.junit.BeforeClass;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class IntegrationTestBase {

    private static boolean initialized = false;

    @BeforeClass
    public static void initEnvironment() throws Exception {
        if (initialized) {
            return;
        }

        App app = App.INJECTOR.getInstance(App.class);
        app.run();

        initialized = true;
    }

    protected WebTarget webTarget(String path) {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:28282/api").path(path);
    }

}
