package hu.rbr.sfinapp.api;

import hu.rbr.sfinapp.model.Account;
import hu.rbr.sfinapp.service.AccountService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	private AccountService service = new AccountService(); 

	@GET
	public List<Account> getAll() {
		List<Account> accs = service.getAllAccounts();
		
		return accs;
	}
}
