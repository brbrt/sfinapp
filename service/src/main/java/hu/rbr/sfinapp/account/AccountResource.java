package hu.rbr.sfinapp.account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	private final AccountService service;

	@Inject
	public AccountResource(AccountService service) {
		this.service = service;
	}

	@GET
	public List<Account> getAll() {
		return service.getAll();
	}
	
	@GET
	@Path("/{id}")
	public Account getById(@PathParam("id") int id) {
		return service.get(id);
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
