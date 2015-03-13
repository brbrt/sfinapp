package hu.rbr.sfinapp.core.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import hu.rbr.sfinapp.core.config.Config;
import hu.rbr.sfinapp.core.config.PropertyConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DirectDbProvider implements DbProvider {

	@Override
	public DataSource getDataSource() {
        Config config = new PropertyConfig();

        MysqlDataSource ds = new MysqlDataSource();

        ds.setURL(config.get("db.url"));
        ds.setUser(config.get("db.username"));
        ds.setPassword(config.get("db.password"));

        return ds;
	}
	
	@Override
	public Connection getConnection() {
		try {
			return getDataSource().getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
