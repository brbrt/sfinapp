package hu.rbr.sfinapp.core.db;

import com.zaxxer.hikari.HikariDataSource;
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
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(config.get("db.url"));
        ds.addDataSourceProperty("user", config.get("db.username"));
        ds.addDataSourceProperty("password", config.get("db.password"));
        ds.setMinimumIdle(config.getInt("db.pool_minimum_idle"));
        ds.setMaximumPoolSize(config.getInt("db.pool_max_size"));

        return ds;
    }
}
