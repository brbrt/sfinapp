package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.account.command.CreateAccountCommand;
import hu.rbr.sfinapp.account.command.DeleteAccountCommand;
import hu.rbr.sfinapp.account.command.UpdateAccountCommand;
import hu.rbr.sfinapp.core.cache.ETagResponseBuilder;
import hu.rbr.sfinapp.core.command.CommandExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

	private final AccountService service;
	private final CommandExecutor commandExecutor;
	private final ETagResponseBuilder eTagResponseBuilder;

	@Inject
	public AccountResource(AccountService service, CommandExecutor commandExecutor, ETagResponseBuilder eTagResponseBuilder) {
		this.service = service;
		this.commandExecutor = commandExecutor;
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
	public void create(CreateAccountCommand command) {
		commandExecutor.execute(command);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(@PathParam("id") int id, UpdateAccountCommand command) {
        command.id = id;
		commandExecutor.execute(command);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer id) {
		commandExecutor.execute(new DeleteAccountCommand(id));
	}

}
