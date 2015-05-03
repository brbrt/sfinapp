package hu.rbr.sfinapp.tag;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

	private final TagService service;

	@Inject
	public TagResource(TagService service, HttpServletRequest request) {
		this.service = service;
	}

	@GET
	public List<Tag> getAll() {
		return service.getAll();
	}
	
	@GET
	@Path("/{id}")
	public Tag getById(@PathParam("id") int id) {
        return service.get(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Tag create(Tag acc) {
		return service.create(acc);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Tag update(@PathParam("id") int id, Tag acc) {
		return service.update(id, acc);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
        service.delete(id);
		return Response.ok().build();
	}
}
