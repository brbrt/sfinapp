package hu.rbr.sfinapp.core.db;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public abstract class BaseDao<E> {

    protected final Sql2o sql2o;
    protected final String tableName;
    protected final Class<E> entityClass;

    public BaseDao(Sql2o sql2o, String tableName, Class<E> entityClass) {
        this.sql2o = sql2o;
        this.tableName = tableName;
        this.entityClass = entityClass;
    }

    protected List<E> getAll(String sql) {
        try (Connection conn = sql2o.open()) {
            List<E> all = conn
                    .createQuery(sql)
                    .executeAndFetch(entityClass);
            return all;
        }
    }

    protected E get(String sql, Integer id) {
        try (Connection conn = sql2o.open()) {
            E item = conn
                    .createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(entityClass);
            return item;
        }
    }

    protected void delete(Connection conn, int id) {
        final String sql = "DELETE FROM " + tableName + " " +
                           " WHERE id = :id";

        conn.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
    }

}
