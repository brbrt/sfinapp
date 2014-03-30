package hu.rbr.sfinapp.service;

import hu.rbr.sfinapp.model.Account;

import java.util.List;

import org.sql2o.Sql2o;


public class AccountService extends AbstractService {
	private Sql2o sql2o = getSql2o();
	
	public List<Account> getAllAccounts() {
		final String sql =
			    "SELECT acc_id as id, " + 
			    "       acc_name as name, " +
			    "       acc_description as description " +
			    "  FROM accounts";

		List<Account> accounts = sql2o.createQuery(sql)
		    .executeAndFetch(Account.class);
		
		return accounts;
	}
	
	public Account getAccountById(int id) {
		final String sql =
			    "SELECT acc_id as id, " + 
			    "       acc_name as name, " +
			    "       acc_description as description " +
			    "  FROM accounts" +
			    " WHERE acc_id = :id";

		Account account = sql2o.createQuery(sql)
			.addParameter("id", id)
		    .executeAndFetchFirst(Account.class);
		
		return account;
	}
	
	public Account createAccount(Account acc) {
		final String sql =
				"INSERT INTO accounts(acc_name, acc_description)" +
				"     VALUES (:name, :description)";

		int newId = sql2o.createQuery(sql, true)
			.addParameter("name", acc.getName())
			.addParameter("description", acc.getDescription())
		    .executeUpdate()
		    .getKey(Integer.class);
		
		return getAccountById(newId);
	}
}
