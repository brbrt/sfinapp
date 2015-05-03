package hu.rbr.sfinapp;

import com.google.inject.AbstractModule;
import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.PropertyConfig;
import hu.rbr.sfinapp.core.db.DataSourceProvider;

import javax.sql.DataSource;

public class SfinappModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Config.class).to(PropertyConfig.class);

        bind(DataSource.class).toProvider(DataSourceProvider.class);
    }
    
}
