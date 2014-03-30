package hu.rbr.sfinapp.service;

import java.sql.Connection;

import javax.sql.DataSource;


public interface DbProvider {

	DataSource getDataSource();
	
	Connection getConnection();
}
