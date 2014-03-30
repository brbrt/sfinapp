package hu.rbr.sfinapp.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class JndiDbProvider implements DbProvider {

	private final static String DS_JNDI_NAME = "jdbc/sfinapp-DS";

	@Override
	public Connection getConnection() {
		try {
			ConnectionPoolDataSource ds = (ConnectionPoolDataSource) new InitialContext().lookup(DS_JNDI_NAME);
			PooledConnection pConn = ds.getPooledConnection();
			
			return pConn.getConnection();
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
