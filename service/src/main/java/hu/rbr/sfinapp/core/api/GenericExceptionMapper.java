package hu.rbr.sfinapp.core.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Response toResponse(Exception ex) {
        logger.warn("Caught exception:", ex);

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message = ex.getMessage();

        return Response
                .status(INTERNAL_SERVER_ERROR)
                .type(APPLICATION_JSON)
                .entity(errorMessage)
                .build();
    }

}
