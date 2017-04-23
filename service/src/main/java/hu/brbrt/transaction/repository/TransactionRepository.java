package hu.brbrt.transaction.repository;

import com.google.common.collect.ImmutableMap;
import hu.brbrt.transaction.Transaction;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final TransactionRowMapper rowMapper;

    public TransactionRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                 TransactionRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Transaction get(int id) {
        final String sql =
                "SELECT id, " +
                "       date, " +
                "       amount, " +
                "       description, " +
                "       account_id, " +
                "       to_account_id, " +
                "       tag_id, " +
                "       comment " +
                "  FROM transactions" +
                " WHERE id = :id";

        return jdbcTemplate.queryForObject(sql, ImmutableMap.of("id", id), rowMapper);
    }

    public int create(Transaction transaction) {
        final String sql =
                "INSERT INTO transactions " +
                "        (date, amount, description, account_id, to_account_id, tag_id, comment) " +
                "VALUES (:date, :amount, :description, :accountId, :toAccountId, :tagId, :comment)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(transaction), keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void update(Transaction transaction) {
        final String sql =
                "UPDATE transactions" +
                "   SET date = :date, " +
                "       amount = :amount, " +
                "       description = :description, " +
                "       account_id = :accountId, " +
                "       to_account_id = :toAccountId, " +
                "       tag_id = :tagId, " +
                "       comment = :comment " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(transaction));
    }

    public void delete(int id) {
        final String sql =
                "DELETE FROM transactions " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, ImmutableMap.of("id", id));
    }

}
