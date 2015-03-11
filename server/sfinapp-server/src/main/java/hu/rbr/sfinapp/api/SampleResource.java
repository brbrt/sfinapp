package hu.rbr.sfinapp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    @GET
    public String get() {
        return "Sample";
    }
}
