package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.util.List;

@Singleton
public class TagDao extends BaseDao<Tag> {

    @Inject
    public TagDao(DataSource dataSource) {
        super(dataSource, "tags", Tag.class);
    }

    public List<Tag> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       parent_id as parentId" +
                "  FROM tags"+
                " ORDER BY name";

        return getAll(sql);
    }

    public Tag get(int id) {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description " +
                "  FROM tags" +
                " WHERE id = :id";

        return get(sql, id);
    }

    public Tag create(Tag tag) {
        final String sql =
                "INSERT INTO tags(name, description, parent_id)" +
                "     VALUES (:name, :description, :parentId)";

        try (Connection conn = sql2o.open()) {
            int newId = conn
                    .createQuery(sql, true)
                    .bind(tag)
                    .executeUpdate()
                    .getKey(Integer.class);

            return get(newId);
        }
    }

    public Tag update(int id, Tag tag) {
        final String sql =
                "UPDATE tags" +
                "   SET name = :name, " +
                "       description = :description, " +
                "       parent_id = :parentId " +
                " WHERE id = :id";

        tag.id = id;

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .bind(tag)
                .executeUpdate();

            return get(id);
        }
    }
}
