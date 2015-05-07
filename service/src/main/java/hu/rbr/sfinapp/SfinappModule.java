package hu.rbr.sfinapp;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.PropertyConfig;
import hu.rbr.sfinapp.core.db.DataSourceProvider;
import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.validation.ValidatorInterceptor;
import hu.rbr.sfinapp.core.validation.ExecutableValidatorProvider;
import hu.rbr.sfinapp.core.validation.ValidatorProvider;

import javax.sql.DataSource;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

public class SfinappModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Config.class).to(PropertyConfig.class);

        bind(DataSource.class).toProvider(DataSourceProvider.class);

        configureValidation();
    }

    private void configureValidation() {
        bind(Validator.class).toProvider(ValidatorProvider.class);
        bind(ExecutableValidator.class).toProvider(ExecutableValidatorProvider.class);

        final ValidatorInterceptor interceptor = new ValidatorInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.subclassesOf(BaseService.class), Matchers.any(), interceptor);
    }


}