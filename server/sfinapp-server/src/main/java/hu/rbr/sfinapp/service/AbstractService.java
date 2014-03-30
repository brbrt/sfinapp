package hu.rbr.sfinapp.service;


public class AbstractService {

	protected DbProvider dbProvider;
	
	public AbstractService() {
		dbProvider = new JndiDbProvider();
	}
}
