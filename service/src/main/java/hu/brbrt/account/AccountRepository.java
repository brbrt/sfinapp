package hu.brbrt.account;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Account> rowMapper;

    public AccountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new BeanPropertyRowMapper<>(Account.class);
    }

    public List<Account> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       technical " +
                "  FROM accounts" +
                " ORDER BY technical, name";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Account get(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       technical " +
                "  FROM accounts" +
                " WHERE id = :id";

        return jdbcTemplate.queryForObject(sql, ImmutableMap.of("id", id), rowMapper);
    }

    public int create(Account account) {
        final String sql =
                "INSERT INTO accounts(name, description, technical)" +
                "     VALUES (:name, :description, :technical)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(account), keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Account account) {
        final String sql =
                "UPDATE accounts" +
                "   SET name = :name, " +
                "       description = :description, " +
                "       technical = :technical " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(account));
    }

    public void delete(int id) {
        final String sql =
                "DELETE FROM accounts " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, ImmutableMap.of("id", id));
    }

}
