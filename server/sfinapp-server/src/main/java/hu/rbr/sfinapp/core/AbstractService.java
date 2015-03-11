package hu.rbr.sfinapp.core;

import hu.rbr.sfinapp.core.db.DbProvider;
import hu.rbr.sfinapp.core.db.DirectDbProvider;
import org.sql2o.Sql2o;


public class AbstractService {

	protected DbProvider dbProvider;

	
	public AbstractService() {
		dbProvider = new DirectDbProvider();
	}


	protected Sql2o getSql2o() {
		return new Sql2o(dbProvider.getDataSource());
	}

}
