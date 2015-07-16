package hu.rbr.sfinapp.core.cache;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.function.Supplier;

public class ETagResponseBuilder {

    private final UriInfo uriInfo;
    private final Request request;

    public ETagResponseBuilder(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }

    public Response build(Supplier<Long> versionFn, Supplier<Object> dataFn) {
        EntityTag eTag = ETagCalculator.calculate(getRequestString(), versionFn.get().toString());

        // Verify that the calculated eTag matches with the eTag in HTTP request.
        Response.ResponseBuilder rb = request.evaluatePreconditions(eTag);

        if (rb == null) {
            // Didn't match, we need to return a full response.
            Object data = dataFn.get();
            rb = Response.ok(data);
        }

        return rb.tag(eTag).build();
    }

    private String getRequestString() {
        URI uri = uriInfo.getRequestUri();
        return uri.getPath() + "?" + uri.getQuery();
    }

}
