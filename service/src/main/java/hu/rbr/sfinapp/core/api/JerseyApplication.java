package hu.rbr.sfinapp.core.api;

import hu.rbr.sfinapp.App;
import hu.rbr.sfinapp.core.hk2toguice.Hk2ToGuiceBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {

    public JerseyApplication() {
        packages(App.class.getPackage().getName());

        register(new Hk2ToGuiceBinder());
    }

}