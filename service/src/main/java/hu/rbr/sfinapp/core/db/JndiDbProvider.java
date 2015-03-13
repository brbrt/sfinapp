package hu.rbr.sfinapp.core.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiDbProvider implements DbProvider {

	private final static String DS_JNDI_NAME = "jdbc/sfinapp-DS";

	@Override
	public DataSource getDataSource() {
		try {
			DataSource ds = (DataSource) new InitialContext().lookup(DS_JNDI_NAME);
			
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
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
