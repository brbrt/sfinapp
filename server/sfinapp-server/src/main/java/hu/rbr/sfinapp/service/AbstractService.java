package hu.rbr.sfinapp.service;

import org.sql2o.QuirksMode;
import org.sql2o.Sql2o;


public class AbstractService {

	protected DbProvider dbProvider;
	
	
	public AbstractService() {
		dbProvider = new JndiDbProvider();
	}
	
	
	protected Sql2o getSql2o() {
		return new Sql2o(dbProvider.getDataSource(), QuirksMode.PostgreSQL);
	}
}
