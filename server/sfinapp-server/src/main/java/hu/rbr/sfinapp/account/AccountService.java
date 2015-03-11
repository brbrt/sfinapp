package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.AbstractService;
import org.sql2o.Sql2o;

import java.util.List;


public class AccountService extends AbstractService {
	private Sql2o sql2o = getSql2o();
	
	public List<Account> getAllAccounts() {
		final String sql =
			    "SELECT id, " +
			    "       name, " +
			    "       description " +
			    "  FROM accounts";

		List<Account> accounts = sql2o.createQuery(sql)
		    .executeAndFetch(Account.class);
		
		return accounts;
	}
	
	public Account getAccountById(int id) {
		final String sql =
            "SELECT id, " +
            "       name, " +
            "       description " +
		    "  FROM accounts" +
		    " WHERE id = :id";

		Account account = sql2o.createQuery(sql)
			.addParameter("id", id)
		    .executeAndFetchFirst(Account.class);
		
		return account;
	}
	
	public Account createAccount(Account acc) {
		final String sql =
			"INSERT INTO accounts(name, description)" +
			"     VALUES (:name, :description)";

		int newId = sql2o.createQuery(sql, true)
			.addParameter("name", acc.getName())
			.addParameter("description", acc.getDescription())
		    .executeUpdate()
		    .getKey(Integer.class);
		
		return getAccountById(newId);
	}
	
	public void updateAccount(int id, Account acc) {
		final String sql =
			"UPDATE accounts" +
			"   SET name = :name, " +
			"       description = :description" +
			" WHERE id = :id";

		sql2o.createQuery(sql)
			.addParameter("id", id)
			.addParameter("name", acc.getName())
			.addParameter("description", acc.getDescription())
		    .executeUpdate();
	}
	
	public void deleteAccount(int id) {
		final String sql =
			"DELETE FROM accounts" +
			" WHERE id = :id";

		sql2o.createQuery(sql)
			.addParameter("id", id)
		    .executeUpdate();
	}
}
