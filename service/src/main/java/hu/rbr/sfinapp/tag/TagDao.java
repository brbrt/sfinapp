package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;

import java.util.List;

public class TagDao extends BaseDao<Tag> {

    public TagDao() {
        super("tags", Tag.class);
    }

    public List<Tag> getAll() {
        final String sql =
                "SELECT id, " +
                "       name, " +
                "       description, " +
                "       parent_id as parentId" +
                "  FROM tags";

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
                    .addParameter("name", tag.name)
                    .addParameter("description", tag.description)
                    .addParameter("parentId", tag.parentId)
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

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .addParameter("id", id)
                .addParameter("name", tag.name)
                .addParameter("description", tag.description)
                .addParameter("parentId", tag.parentId)
                .executeUpdate();

            return get(id);
        }
    }
}
