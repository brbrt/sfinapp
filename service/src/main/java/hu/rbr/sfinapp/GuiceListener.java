package hu.rbr.sfinapp;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return App.INJECTOR;
    }

}
