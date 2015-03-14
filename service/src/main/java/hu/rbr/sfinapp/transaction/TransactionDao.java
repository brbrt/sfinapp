package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;

import java.util.List;

public class TransactionDao extends BaseDao<Transaction> {

    public TransactionDao() {
        super("transactions", Transaction.class);
    }
    public List<Transaction> getAll() {
        final String sql =
                "SELECT id, " +
                "       date, " +
                "       amount, " +
                "       description, " +
                "       account_id as accountId, " +
                "       comment " +
                "  FROM transactions";

        return getAll(sql);
    }

    public Transaction get(int id) {
        final String sql =
                "SELECT id, " +
                "       date, " +
                "       amount, " +
                "       description, " +
                "       account_id as accountId, " +
                "       comment " +
                "  FROM transactions" +
                " WHERE id = :id";

        return get(sql, id);
    }

    public Transaction create(Transaction transaction) {
        final String sql =
                "INSERT INTO transactions(date, amount, description, account_id, comment) " +
                              " VALUES (:date, :amount, :description, :accountId, :comment)";

        try (Connection conn = sql2o.open()) {
            int newId = conn
                    .createQuery(sql, true)
                    .bind(transaction)
                    .executeUpdate()
                    .getKey(Integer.class);

            return get(newId);
        }
    }
    @Override
    public Transaction update(int id, Transaction transaction) {
        final String sql =
                "UPDATE transactions" +
                "   SET date = :date, " +
                "       amount = :amount, " +
                "       description = :description, " +
                "       account_id = :accountId, " +
                "       comment = :comment " +
                " WHERE id = :id";

        transaction.id = id;

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .bind(transaction)
                .executeUpdate();

            return get(id);
        }
    }
}
