package hu.rbr.sfinapp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("account")
public class Account {

	@GET
	public String hello() {
		return "Hello";
	}
}
