package hu.rbr.sfinapp;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import hu.rbr.sfinapp.account.command.CreateAccountCommandHandler;
import hu.rbr.sfinapp.account.command.DeleteAccountCommandHandler;
import hu.rbr.sfinapp.account.command.UpdateAccountCommandHandler;
import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.TypeSafeConfig;
import hu.rbr.sfinapp.core.db.DataSourceProvider;
import hu.rbr.sfinapp.core.db.Sql2oProvider;
import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.validation.ValidatorInterceptor;
import hu.rbr.sfinapp.core.validation.ExecutableValidatorProvider;
import hu.rbr.sfinapp.core.validation.ValidatorProvider;
import hu.rbr.sfinapp.core.version.InMemoryVersionStore;
import hu.rbr.sfinapp.core.version.VersionStore;
import hu.rbr.sfinapp.core.version.VersionedOperation;
import hu.rbr.sfinapp.core.version.VersionedOperationInterceptor;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

public class SfinappModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Config.class).to(TypeSafeConfig.class).in(Singleton.class);

        bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);
        bind(Sql2o.class).toProvider(Sql2oProvider.class).in(Singleton.class);

        configureValidation();
        configureVersioning();

        configureHandlers();
    }

    private void configureValidation() {
        bind(Validator.class).toProvider(ValidatorProvider.class).in(Singleton.class);
        bind(ExecutableValidator.class).toProvider(ExecutableValidatorProvider.class).in(Singleton.class);

        final ValidatorInterceptor interceptor = new ValidatorInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.subclassesOf(BaseService.class), Matchers.any(), interceptor);
    }

    private void configureVersioning() {
        bind(VersionStore.class).to(InMemoryVersionStore.class).in(Singleton.class);

        final VersionedOperationInterceptor interceptor = new VersionedOperationInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.subclassesOf(BaseService.class), Matchers.annotatedWith(VersionedOperation.class), interceptor);
    }

    private void configureHandlers() {
        Multibinder<Handler> multibinder = Multibinder.newSetBinder(binder(), Handler.class);
        multibinder.addBinding().to(CreateAccountCommandHandler.class);
        multibinder.addBinding().to(DeleteAccountCommandHandler.class);
        multibinder.addBinding().to(UpdateAccountCommandHandler.class);
    }

}
