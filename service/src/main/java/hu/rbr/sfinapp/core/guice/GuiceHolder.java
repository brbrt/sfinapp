package hu.rbr.sfinapp.core.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.rbr.sfinapp.SfinappModule;

public class GuiceHolder {

    private static Injector injector;

    public static Injector getInjector() {
        if (injector == null) {
            injector = Guice.createInjector(new SfinappModule());
        }

        return injector;
    }

}
