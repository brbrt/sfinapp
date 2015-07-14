package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.cache.ETagResponseBuilder;
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
	
	private final TransactionService service;
	private final ETagResponseBuilder eTagResponseBuilder;

	@Inject
    public TransactionResource(TransactionService service, ETagResponseBuilder eTagResponseBuilder) {
        this.service = service;
        this.eTagResponseBuilder = eTagResponseBuilder;
    }

    @GET
	public Response getAll(@QueryParam("from") String from,
                           @QueryParam("to") String to,
                           @QueryParam("description") String description,
                           @QueryParam("tag") String tag) {

        TransactionListFilter filter = new TransactionListFilter(parseAsDate(from), parseAsDate(to), description, tag);
		return eTagResponseBuilder.build(service::getVersion, () -> service.getAll(filter));
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
	public Transaction create(Transaction transaction) {
		return service.create(transaction);
	}

	@POST
	@Path("/batch")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createBatch(List<Transaction> transactions) {
		service.createBatch(transactions);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Transaction update(@PathParam("id") int id, Transaction transaction) {
		return service.update(id, transaction);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
        service.delete(id);
		return Response.ok().build();
	}

}
