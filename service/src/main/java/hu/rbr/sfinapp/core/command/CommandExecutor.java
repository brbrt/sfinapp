package hu.rbr.sfinapp.core.command;

import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Singleton
public class CommandExecutor {

    private final Set<Handler> handlers;
    private final Validator validator;

    @Inject
    public CommandExecutor(Set<Handler> handlers, Validator validator) {
        this.handlers = handlers;
        this.validator = validator;
    }

    public void execute(Command command) {
        validate(command);

        Handler<Command> handler = findHandler(command);
        handler.handle(command);
    }

    private void validate(Command command) {
        Set<ConstraintViolation<Command>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation errors!", violations);
        }
    }

    @SuppressWarnings("unchecked")
    private Handler<Command> findHandler(Command command) {
        for (Handler handler : handlers) {
            if (handler.commandClass() == command.getClass()) {
                return (Handler<Command>)handler;
            }
        }

        throw new RuntimeException("No handler found for " + command.getClass().getSimpleName());
    }

}
