package hu.rbr.sfinapp.core.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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

        ds.setDataSourceClassName(MysqlDataSource.class.getName());
        ds.addDataSourceProperty("serverName", config.get("db.server"));
        ds.addDataSourceProperty("port", config.getInt("db.port"));
        ds.addDataSourceProperty("databaseName", config.get("db.database_name"));
        ds.addDataSourceProperty("user", config.get("db.username"));
        ds.addDataSourceProperty("password", config.get("db.password"));

        ds.setMaximumPoolSize(config.getInt("db.max_pool_size"));
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 25);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return ds;
    }
}
