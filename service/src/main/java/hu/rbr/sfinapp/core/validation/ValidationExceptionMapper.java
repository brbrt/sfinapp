package hu.rbr.sfinapp.core.validation;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import hu.rbr.sfinapp.core.api.ErrorMessage;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message = getMessage(exception.getConstraintViolations());

        return Response
                .status(BAD_REQUEST)
                .type(APPLICATION_JSON)
                .entity(errorMessage)
                .build();
    }

    private String getMessage(final Set<? extends ConstraintViolation<?>> violations) {
        return Joiner
                .on("  ")
                .skipNulls()
                .join(Iterables.transform(violations, new ViolationTransformer()));
    }

    private static class ViolationTransformer implements Function<ConstraintViolation<?>, String> {
        @Override
        public String apply(ConstraintViolation<?> violation) {
            return violation.getMessage();
        }
    }

}
