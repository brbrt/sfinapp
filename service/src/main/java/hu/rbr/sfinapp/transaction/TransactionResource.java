package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.transaction.list.TransactionListItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
	
	private TransactionService service = new TransactionService();

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
	public Transaction create(Transaction acc) {
		return service.create(acc);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Transaction update(@PathParam("id") int id, Transaction acc) {
		return service.update(id, acc);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
        service.delete(id);
		return Response.ok().build();
	}
}
