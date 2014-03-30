package hu.rbr.sfinapp.service;

import java.sql.Connection;

public interface DbProvider {

	Connection getConnection();
}
