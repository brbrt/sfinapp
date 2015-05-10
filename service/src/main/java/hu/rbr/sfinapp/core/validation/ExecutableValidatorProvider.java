package hu.rbr.sfinapp.core.validation;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

public class ExecutableValidatorProvider implements Provider<ExecutableValidator> {

    private final Validator validator;

    @Inject
    public ExecutableValidatorProvider(Validator validator) {
        this.validator = validator;
    }

    @Override
    public ExecutableValidator get() {
        return validator.forExecutables();
    }

}
