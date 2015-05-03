package hu.rbr.sfinapp.core.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import hu.rbr.sfinapp.core.config.Config;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

public class DataSourceProvider implements Provider<DataSource> {

    private final Config config;

    @Inject
    public DataSourceProvider(Config config) {
        this.config = config;
    }

    @Override
    public DataSource get() {
        MysqlDataSource ds = new MysqlDataSource();

        ds.setURL(config.get("db.url"));
        ds.setUser(config.get("db.username"));
        ds.setPassword(config.get("db.password"));

        return ds;
    }
}
