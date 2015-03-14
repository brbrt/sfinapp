package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;

import java.util.List;

public class AccountDao extends BaseDao<Account> {

    public AccountDao() {
        super("accounts", Account.class);
    }

    public List<Account> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM accounts";

        return getAll(sql);
    }

    public Account get(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM accounts" +
                " WHERE id = :id";

        return get(sql, id);
    }

    public Account create(Account acc) {
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

            return get(newId);
        }
    }

    public Account update(int id, Account acc) {
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

            return get(id);
        }
    }

}
