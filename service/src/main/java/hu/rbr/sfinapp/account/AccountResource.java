package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.cache.ETagResponseBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	private final AccountService service;
    private final ETagResponseBuilder eTagResponseBuilder;

    @Inject
    public AccountResource(AccountService service, ETagResponseBuilder eTagResponseBuilder) {
        this.service = service;
        this.eTagResponseBuilder = eTagResponseBuilder;
    }

	@GET
	public Response getAll() {
		return eTagResponseBuilder.build(service::getVersion, service::getAll);
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		return eTagResponseBuilder.build(service::getVersion, () -> service.get(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Account create(Account acc) {
        return service.create(acc);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Account update(@PathParam("id") int id, Account acc) {
		return service.update(id, acc);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		service.delete(id);
		return Response.ok().build();
	}

}
