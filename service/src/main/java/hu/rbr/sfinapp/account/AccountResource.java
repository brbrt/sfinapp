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

	private final AccountQueryService service;
	private final CommandExecutor commandExecutor;
	private final ETagResponseBuilder eTagResponseBuilder;

	@Inject
	public AccountResource(AccountQueryService service, CommandExecutor commandExecutor, ETagResponseBuilder eTagResponseBuilder) {
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
	public Response create(CreateAccountCommand command) {
		commandExecutor.execute(command);

		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, UpdateAccountCommand command) {
        command.id = id;
		commandExecutor.execute(command);

		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		commandExecutor.execute(new DeleteAccountCommand(id));

		return Response.ok().build();
	}

}
