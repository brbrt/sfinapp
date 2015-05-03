package hu.rbr.sfinapp.core.hk2toguice;

import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Inject;
import javax.inject.Singleton;

public class Hk2ToGuiceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(Hk2ToGuiceInjectionResolver.class)
            .to(new TypeLiteral<InjectionResolver<Inject>>() {})
            .in(Singleton.class)
            .ranked(1000);
    }

}
