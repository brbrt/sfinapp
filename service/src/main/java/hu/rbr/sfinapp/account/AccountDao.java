package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AccountDao extends BaseDao<Account> {

    @Inject
    public AccountDao(Sql2o sql2o) {
        super(sql2o, "accounts", Account.class);
    }

    public List<Account> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       technical " +
                "  FROM accounts" +
                " ORDER BY technical, name";

        return getAll(sql);
    }

    public Account get(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       technical " +
                "  FROM accounts" +
                " WHERE id = :id";

        return get(sql, id);
    }

    public Account create(Account acc) {
        final String sql =
                "INSERT INTO accounts(name, description, technical)" +
                "     VALUES (:name, :description, :technical)";

        try (Connection conn = sql2o.open()) {
            int newId = conn
                    .createQuery(sql, true)
                    .bind(acc)
                    .executeUpdate()
                    .getKey(Integer.class);

            return get(newId);
        }
    }

    public Account update(int id, Account acc) {
        final String sql =
                "UPDATE accounts" +
                "   SET name = :name, " +
                "       description = :description, " +
                "       technical = :technical " +
                " WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .bind(acc)
                .executeUpdate();

            return get(id);
        }
    }

    public void delete(int id) {
        try (Connection conn = sql2o.open()) {
            delete(conn, id);
        }
    }

}
