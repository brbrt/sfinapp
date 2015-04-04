package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;
import org.sql2o.Query;

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

        List<Transaction> transactions = getAll(sql);

        for (Transaction transaction : transactions) {
            loadTags(transaction);
        }

        return transactions;
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

        Transaction transaction = get(sql, id);
        return loadTags(transaction);
    }

    public Transaction create(Transaction transaction) {
        final String sql =
                "INSERT INTO transactions " +
                "        (date, amount, description, account_id, comment) " +
                "VALUES (:date, :amount, :description, :accountId, :comment)";

        try (Connection conn = sql2o.open()) {
            int newId = conn
                    .createQuery(sql, true)
                    .bind(transaction)
                    .executeUpdate()
                    .getKey(Integer.class);

            transaction.id = newId;
            saveTags(transaction);

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
                "       comment = :comment " +
                " WHERE id = :id";

        transaction.id = id;

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .bind(transaction)
                .executeUpdate();

            saveTags(transaction);

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

        transaction.tags = getTags(transaction.id);
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

    private void saveTags(final Transaction transaction) {
        try (Connection conn = sql2o.open()) {
            final String deleteSql = "DELETE FROM transaction_tags " +
                                     " WHERE transaction_id = :transactionId";

            conn.createQuery(deleteSql)
                .addParameter("transactionId", transaction.id)
                .executeUpdate();


            final String insertSql = "INSERT INTO transaction_tags" +
                                     "       (transaction_id, tag_id) " +
                                     "VALUES (:transactionId, :tagId) ";

            Query query = conn.createQuery(insertSql);

            for (Integer tagId : transaction.tags) {
                query.addParameter("transactionId", transaction.id)
                     .addParameter("tagId", tagId)
                     .addToBatch();
            }

            query.executeBatch();
        }
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
