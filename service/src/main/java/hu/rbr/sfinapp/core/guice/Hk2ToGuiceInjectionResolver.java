package hu.rbr.sfinapp.core.guice;

import com.google.inject.Key;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Type;

@Singleton
public class Hk2ToGuiceInjectionResolver implements InjectionResolver<Inject> {

    @Inject @Named(InjectionResolver.SYSTEM_RESOLVER_NAME)
    private InjectionResolver<Inject> hk2SystemResolver;

    @Override
    public Object resolve(Injectee injectee, ServiceHandle<?> serviceHandle) {
        try {
            return hk2SystemResolver.resolve(injectee, serviceHandle);
        } catch (Exception ex) {
            Type requiredType = injectee.getRequiredType();
            return GuiceHolder.getInjector().getInstance(Key.get(requiredType));
        }
    }

    @Override
    public boolean isConstructorParameterIndicator() {
        return true;
    }

    @Override
    public boolean isMethodParameterIndicator() {
        return true;
    }

}
