package hu.brbrt.tag;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Tag> rowMapper;

    public TagRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new BeanPropertyRowMapper<>(Tag.class);
    }

    public List<Tag> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM tags " +
                " ORDER BY name";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Tag get(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM tags " +
                " WHERE id = :id";

        return jdbcTemplate.queryForObject(sql, ImmutableMap.of("id", id), rowMapper);
    }

    public void create(Tag tag) {
        final String sql =
                "INSERT INTO tags(name, description)" +
                "     VALUES (:name, :description)";

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tag));
    }

    public void update(Tag account) {
        final String sql =
                "UPDATE tags" +
                "   SET name = :name, " +
                "       description = :description " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(account));
    }

    public void delete(int id) {
        final String sql =
                "DELETE FROM tags " +
                " WHERE id = :id";

        jdbcTemplate.update(sql, ImmutableMap.of("id", id));
    }

}
