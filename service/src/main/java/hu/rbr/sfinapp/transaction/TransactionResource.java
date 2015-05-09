package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.transaction.list.TransactionListItem;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
	
	private final TransactionService service;

	@Inject
	public TransactionResource(TransactionService service) {
		this.service = service;
	}

	@GET
	public List<TransactionListItem> getAll() {
		return service.getAll();
	}
	
	@GET
	@Path("/{id}")
	public Transaction getById(@PathParam("id") int id) {
        return service.get(id);
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
