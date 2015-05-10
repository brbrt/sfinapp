package hu.rbr.sfinapp.core.db;

import hu.rbr.sfinapp.core.api.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2oException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.sql.SQLIntegrityConstraintViolationException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class Sql2oExceptionMapper implements ExceptionMapper<Sql2oException> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Response toResponse(Sql2oException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message =  extractMessage(exception);

        return Response
                .status(INTERNAL_SERVER_ERROR)
                .type(APPLICATION_JSON)
                .entity(errorMessage)
                .build();
    }

    private String extractMessage(Sql2oException exception) {
        Throwable cause = exception.getCause();

        if (cause instanceof SQLIntegrityConstraintViolationException) {
            String violationMessage = cause.getMessage();
            log.info("DB constraint violation: {}", violationMessage);
            return violationMessage;
        }

        log.warn("DB error", exception);
        return exception.getMessage();
    }
}
