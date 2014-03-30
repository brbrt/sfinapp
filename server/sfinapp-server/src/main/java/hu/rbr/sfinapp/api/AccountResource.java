package hu.rbr.sfinapp.api;

import hu.rbr.sfinapp.model.Account;
import hu.rbr.sfinapp.service.AccountService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

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
	public Account createAccount(Account acc) {
		Account newAcc = service.createAccount(acc);
		
		return newAcc;
	}
}
