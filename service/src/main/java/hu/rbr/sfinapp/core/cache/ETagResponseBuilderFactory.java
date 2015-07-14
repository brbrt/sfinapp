package hu.rbr.sfinapp.core.cache;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class ETagResponseBuilderFactory implements Factory<ETagResponseBuilder> {

    private UriInfo uriInfo;
    private Request request;

    @Inject
    public ETagResponseBuilderFactory(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }

    @Override
    public ETagResponseBuilder provide() {
        return new ETagResponseBuilder(uriInfo, request);
    }

    @Override
    public void dispose(ETagResponseBuilder ETagResponseBuilder) {
    }

}
