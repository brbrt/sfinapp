package hu.rbr.sfinapp.util;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ApplicationChecker {

    private static final int CHECK_INTERVAL = 1;
    private static final int TIMEOUT = 20;

    public static void waitUntilAvailable(String testUrl) throws InterruptedException {
        for (int i = 0; i < TIMEOUT; i++) {
            Thread.sleep(CHECK_INTERVAL * 1000);

            if (isAvailable(testUrl)) {
                return;
            }
        }

        throw new RuntimeException("Application is not available.");
    }

    private static boolean isAvailable(String url) {
        try {
            Response response = ClientBuilder
                    .newClient()
                    .target(url)
                    .request()
                    .get();

            return response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL;
        } catch (Exception ex) {
            return false;
        }
    }

}
