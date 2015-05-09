package hu.rbr.sfinapp.transaction;

import org.sql2o.Connection;
import org.sql2o.Query;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;

@Singleton
public class TransactionTagDaoHelper {

    public List<Integer> getTags(Connection conn, int transactionId) {
        final String sql = "SELECT tag_id " +
                           "  FROM transaction_tags " +
                           " WHERE transaction_id = :transactionId";

        return conn
                .createQuery(sql)
                .addParameter("transactionId", transactionId)
                .executeScalarList(Integer.class);
    }

    public void saveTags(Connection conn, int transactionId, Collection<Integer> tagIds) {
        final String sql = "INSERT INTO transaction_tags" +
                           "       (transaction_id, tag_id) " +
                           "VALUES (:transactionId, :tagId) ";

        Query query = conn.createQuery(sql);

        for (Integer tagId : tagIds) {
            query.addParameter("transactionId", transactionId)
                    .addParameter("tagId", tagId)
                    .addToBatch();
        }

        query.executeBatch();
    }

    public void deleteTags(Connection conn, int transactionId) {
        final String sql = "DELETE FROM transaction_tags " +
                           " WHERE transaction_id = :transactionId";

        conn.createQuery(sql)
                .addParameter("transactionId", transactionId)
                .executeUpdate();
    }

}
