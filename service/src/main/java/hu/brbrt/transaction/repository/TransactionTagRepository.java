package hu.brbrt.transaction.repository;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionTagRepository {

    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TransactionTagRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> loadTags(int transactionId) {
        final String sql =
                "SELECT tag_id " +
                "  FROM transaction_tags " +
                " WHERE transaction_id = :transactionId";

        return jdbcTemplate.queryForList(sql, ImmutableMap.of("transactionId", transactionId), Integer.class);
    }

    public void saveTags(int transactionId, List<Integer> tags) {
        final String sql =
                "INSERT INTO transaction_tags" +
                "       (transaction_id, tag_id) " +
                "VALUES (?, ?) ";

        new JdbcTemplate(dataSource).batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, transactionId);
                ps.setInt(2, tags.get(i));
            }

            @Override
            public int getBatchSize() {
                return tags.size();
            }
        });
    }

    public void deleteTags(int transactionId) {
        final String sql =
                "DELETE FROM transaction_tags " +
                " WHERE transaction_id = :transactionId";

        jdbcTemplate.update(sql, ImmutableMap.of("transactionId", transactionId));
    }

}
