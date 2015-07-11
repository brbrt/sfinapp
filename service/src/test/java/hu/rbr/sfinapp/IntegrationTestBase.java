package hu.rbr.sfinapp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class IntegrationTestBase {

    protected WebTarget webTarget(String path) {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:28282/api").path(path);
    }

}
