package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;

import java.util.List;

public class AccountDao extends BaseDao<Account> {

    public List<Account> getAllAccounts() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM accounts";

        try (Connection conn = sql2o.open()) {
            List<Account> accounts = conn
                    .createQuery(sql)
                    .executeAndFetch(Account.class);
            return accounts;
        }
    }

    public Account getAccountById(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM accounts" +
                " WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            Account account = conn
                    .createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Account.class);
            return account;
        }

    }

    public Account createAccount(Account acc) {
        final String sql =
                "INSERT INTO accounts(name, description)" +
                "     VALUES (:name, :description)";

        try (Connection conn = sql2o.open()) {
            int newId = conn
                    .createQuery(sql, true)
                    .addParameter("name", acc.name)
                    .addParameter("description", acc.description)
                    .executeUpdate()
                    .getKey(Integer.class);

            return getAccountById(newId);
        }
    }

    public void updateAccount(int id, Account acc) {
        final String sql =
                "UPDATE accounts" +
                "   SET name = :name, " +
                "       description = :description" +
                " WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .addParameter("id", id)
                .addParameter("name", acc.name)
                .addParameter("description", acc.description)
                .executeUpdate();
        }
    }

    public void deleteAccount(int id) {
        final String sql =
                "DELETE FROM accounts" +
                " WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
