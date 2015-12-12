package hu.rbr.sfinapp.transaction;

import com.google.common.collect.ImmutableList;
import hu.rbr.sfinapp.core.cache.ETagResponseBuilder;
import hu.rbr.sfinapp.core.command.CommandExecutor;
import hu.rbr.sfinapp.transaction.command.CreateTransactionsCommand;
import hu.rbr.sfinapp.transaction.command.DeleteTransactionCommand;
import hu.rbr.sfinapp.transaction.command.TransactionItem;
import hu.rbr.sfinapp.transaction.command.UpdateTransactionCommand;
import hu.rbr.sfinapp.transaction.list.TransactionListFilter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static hu.rbr.sfinapp.core.api.Dates.parseAsDate;

@Path("transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private final CommandExecutor commandExecutor;
	private final TransactionQueryService service;
	private final ETagResponseBuilder eTagResponseBuilder;

	@Inject
    public TransactionResource(CommandExecutor commandExecutor, TransactionQueryService service, ETagResponseBuilder eTagResponseBuilder) {
        this.commandExecutor = commandExecutor;
        this.service = service;
        this.eTagResponseBuilder = eTagResponseBuilder;
    }

    @GET
	public Response getAll(@QueryParam("from") String from,
                           @QueryParam("to") String to,
                           @QueryParam("description") String description,
                           @QueryParam("tag") String tag) {

        TransactionListFilter filter = new TransactionListFilter(parseAsDate(from), parseAsDate(to), description, tag);
		return eTagResponseBuilder.build(service::getListVersion, () -> service.getAll(filter));
	}

    @GET
    @Path("/descriptions")
	public Response getAllDescriptions() {
        return eTagResponseBuilder.build(service::getVersion, service::getAllDescriptions);
    }
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
        return eTagResponseBuilder.build(service::getVersion, () -> service.get(id));
	}

	@GET
	@Path("/skeleton")
	public Transaction skeleton() {
		return service.skeleton();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(TransactionItem transaction) {
        commandExecutor.execute(new CreateTransactionsCommand(ImmutableList.of(transaction)));

        return Response.ok().build();
	}

	@POST
	@Path("/batch")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBatch(List<TransactionItem> transactions) {
        commandExecutor.execute(new CreateTransactionsCommand(transactions));

		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, TransactionItem transaction) {
		commandExecutor.execute(new UpdateTransactionCommand(id, transaction));

		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
        commandExecutor.execute(new DeleteTransactionCommand(id));

		return Response.ok().build();
	}

}
