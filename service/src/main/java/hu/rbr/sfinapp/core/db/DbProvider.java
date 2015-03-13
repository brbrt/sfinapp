package hu.rbr.sfinapp.core.db;

import java.sql.Connection;

import javax.sql.DataSource;


public interface DbProvider {

	DataSource getDataSource();
	
	Connection getConnection();
}
