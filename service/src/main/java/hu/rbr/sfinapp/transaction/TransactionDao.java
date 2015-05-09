package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TransactionDao extends BaseDao<Transaction> {

    private final TransactionTagDaoHelper tagHelper;

    @Inject
    public TransactionDao(Sql2o sql2o, TransactionTagDaoHelper tagHelper) {
        super(sql2o, "transactions", Transaction.class);

        this.tagHelper = tagHelper;
    }

    public Transaction get(int id) {
        final String sql =
                "SELECT id, " +
                "       date, " +
                "       amount, " +
                "       description, " +
                "       account_id as accountId, " +
                "       to_account_id as toAccountId, " +
                "       comment " +
                "  FROM transactions" +
                " WHERE id = :id";

        Transaction transaction = get(sql, id);
        return loadTags(transaction);
    }

    public Transaction create(Transaction transaction) {
        try (Connection conn = sql2o.beginTransaction()) {
            int newId = create(conn, transaction);
            conn.commit();
            return get(newId);
        }
    }

    public void createBatch(List<Transaction> transactions) {
        try (Connection conn = sql2o.beginTransaction()) {
            for (Transaction transaction : transactions) {
                create(conn, transaction);
            }

            conn.commit();
        }
    }

    private int create(Connection conn, Transaction transaction) {
        final String sql =
                "INSERT INTO transactions " +
                "        (date, amount, description, account_id, to_account_id, comment) " +
                "VALUES (:date, :amount, :description, :accountId, :toAccountId, :comment)";

        int newId = conn
                .createQuery(sql, true)
                .bind(transaction)
                .executeUpdate()
                .getKey(Integer.class);

        tagHelper.deleteTags(conn, newId);
        tagHelper.saveTags(conn, newId, transaction.tagIds);

        return newId;
    }

    public Transaction update(int id, Transaction transaction) {
        final String sql =
                "UPDATE transactions" +
                "   SET date = :date, " +
                "       amount = :amount, " +
                "       description = :description, " +
                "       account_id = :accountId, " +
                "       to_account_id = :toAccountId, " +
                "       comment = :comment " +
                " WHERE id = :id";

        transaction.id = id;

        try (Connection conn = sql2o.beginTransaction()) {
            conn.createQuery(sql)
                .bind(transaction)
                .executeUpdate();

            tagHelper.deleteTags(conn, transaction.id);
            tagHelper.saveTags(conn, transaction.id, transaction.tagIds);

            conn.commit();

            return get(id);
        }
    }

    public void delete(final int id) {
        try (Connection conn = sql2o.beginTransaction()) {
            tagHelper.deleteTags(conn, id);
            super.delete(conn, id);

            conn.commit();
        }
    }

    private Transaction loadTags(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        try (Connection conn = sql2o.open()) {
            transaction.tagIds = tagHelper.getTags(conn, transaction.id);
            return transaction;
        }
    }

}
