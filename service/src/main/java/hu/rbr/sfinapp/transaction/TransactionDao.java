package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TransactionDao extends BaseDao<Transaction> {

    @Inject
    public TransactionDao(Sql2o sql2o) {
        super(sql2o, "transactions", Transaction.class);
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
        final String sql =
                "INSERT INTO transactions " +
                "        (date, amount, description, account_id, to_account_id, comment) " +
                "VALUES (:date, :amount, :description, :accountId, :toAccountId, :comment)";

        try (Connection conn = sql2o.beginTransaction()) {
            int newId = conn
                    .createQuery(sql, true)
                    .bind(transaction)
                    .executeUpdate()
                    .getKey(Integer.class);

            transaction.id = newId;
            saveTags(conn, transaction);

            conn.commit();

            return get(newId);
        }
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

            saveTags(conn, transaction);

            conn.commit();

            return get(id);
        }
    }

    @Override
    public void delete(final int id) {
        deleteTags(id);
        super.delete(id);
    }

    private Transaction loadTags(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        transaction.tagIds = getTags(transaction.id);
        return transaction;
    }

    private List<Integer> getTags(final int transactionId) {
        final String sql =
                "SELECT tag_id " +
                "  FROM transaction_tags " +
                " WHERE transaction_id = :transactionId";

        try (Connection conn = sql2o.open()) {
            return conn
                    .createQuery(sql)
                    .addParameter("transactionId", transactionId)
                    .executeScalarList(Integer.class);
        }
    }

    private void saveTags(Connection conn, final Transaction transaction) {
        final String deleteSql = "DELETE FROM transaction_tags " +
                                 " WHERE transaction_id = :transactionId";

        conn.createQuery(deleteSql)
                .addParameter("transactionId", transaction.id)
                .executeUpdate();


        final String insertSql = "INSERT INTO transaction_tags" +
                                 "       (transaction_id, tag_id) " +
                                 "VALUES (:transactionId, :tagId) ";

        Query query = conn.createQuery(insertSql);

        for (Integer tagId : transaction.tagIds) {
            query.addParameter("transactionId", transaction.id)
                 .addParameter("tagId", tagId)
                 .addToBatch();
        }

        query.executeBatch();
    }

    private void deleteTags(final int transactionId) {
        final String sql =
                "DELETE FROM transaction_tags " +
                      "WHERE transaction_id = :transactionId";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("transactionId", transactionId)
                    .executeUpdate();
        }
    }

}
