package hu.rbr.sfinapp.account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.List;

@Path("account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	private AccountService service = new AccountService(); 

	@GET
	public List<Account> getAll() {
		List<Account> accs = service.getAllAccounts();
		
		return accs;
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		Account acc = service.getAccountById(id);
		
		ResponseBuilder resp = (acc == null) ? Response.ok("Not found") : Response.ok(acc);
		
		return resp.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Account create(Account acc) {
		Account newAcc = service.createAccount(acc);
		
		return newAcc;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Account acc) {
		service.updateAccount(id, acc);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		service.deleteAccount(id);
		
		return Response.ok().build();
	}
}
