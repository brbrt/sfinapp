package hu.rbr.sfinapp.core.db;

import org.sql2o.Sql2o;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

public class Sql2oProvider implements Provider<Sql2o> {

    private final DataSource dataSource;

    @Inject
    public Sql2oProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Sql2o get() {
        return new Sql2o(dataSource);
    }

}
