package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.cache.ETagResponseBuilder;
import hu.rbr.sfinapp.core.command.CommandExecutor;
import hu.rbr.sfinapp.tag.command.CreateTagCommand;
import hu.rbr.sfinapp.tag.command.DeleteTagCommand;
import hu.rbr.sfinapp.tag.command.UpdateTagCommand;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

	private final CommandExecutor commandExecutor;
	private final TagQueryService service;
	private final ETagResponseBuilder eTagResponseBuilder;

	@Inject
	public TagResource(CommandExecutor commandExecutor, TagQueryService service, ETagResponseBuilder eTagResponseBuilder) {
		this.commandExecutor = commandExecutor;
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
	public Response create(CreateTagCommand command) {
		commandExecutor.execute(command);

        return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, UpdateTagCommand command) {
        command.id = id;
        commandExecutor.execute(command);

        return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		commandExecutor.execute(new DeleteTagCommand(id));

        return Response.ok().build();
	}

}
