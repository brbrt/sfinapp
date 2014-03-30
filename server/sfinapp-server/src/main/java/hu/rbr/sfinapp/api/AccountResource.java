package hu.rbr.sfinapp.api;

import hu.rbr.sfinapp.model.Account;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

	@GET
	public List<Account> getAll() {
		List<Account> accs = new ArrayList<>();
		
		Account a1 = new Account(1, "AN1", "decr");
		Account a2 = new Account(3, "AN3", "gfdklfgdklkjlgfdkj");
		
		accs.add(a1);
		accs.add(a2);
		
		return accs;
	}
}
