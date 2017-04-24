package hu.brbrt.overview;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OverviewRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final OverviewItemRowMapper overviewItemRowMapper;

    public OverviewRepository(NamedParameterJdbcTemplate jdbcTemplate, OverviewItemRowMapper overviewItemRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.overviewItemRowMapper = overviewItemRowMapper;
    }

    public List<OverviewItem> get() {
        final String sql =
                "SELECT transaction_id," +
                "       description," +
                "       date," +
                "       amount," +
                "       comment," +
                "       account_name," +
                "       to_account_name," +
                "       tag_name " +
                "  FROM overview";

        return jdbcTemplate.query(sql, ImmutableMap.of(), overviewItemRowMapper);
    }

}
