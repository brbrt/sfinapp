package hu.rbr.sfinapp.core.validation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.executable.ExecutableValidator;
import java.util.Set;

@Singleton
public class ValidatorInterceptor implements MethodInterceptor {

    @Inject
    private ExecutableValidator validator;

    @Override
    public Object invoke(MethodInvocation ctx) throws Throwable {
        Set<ConstraintViolation<Object>> violations = validator.validateParameters(
                ctx.getThis(), ctx.getMethod(), ctx.getArguments()
        );

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation errors!", violations);
        }

        return ctx.proceed();
    }

}
