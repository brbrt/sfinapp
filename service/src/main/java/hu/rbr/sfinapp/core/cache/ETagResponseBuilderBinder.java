package hu.rbr.sfinapp.core.cache;

import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ETagResponseBuilderBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(ETagResponseBuilderFactory.class).to(ETagResponseBuilder.class).in(PerLookup.class);
    }

}
