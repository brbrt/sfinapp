package hu.rbr.sfinapp.core.db;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.util.List;

public abstract class BaseDao<E> {

    protected final DataSource dataSource;
    protected final String tableName;
    protected final Class<E> entityClass;
    protected final Sql2o sql2o;

    public BaseDao(DataSource dataSource, String tableName, Class<E> entityClass) {
        this.dataSource = dataSource;
        this.tableName = tableName;
        this.entityClass = entityClass;

        this.sql2o = new Sql2o(dataSource);
    }

    protected List<E> getAll(final String sql) {
        try (Connection conn = sql2o.open()) {
            List<E> all = conn
                    .createQuery(sql)
                    .executeAndFetch(entityClass);
            return all;
        }
    }

    protected E get(final String sql, Integer id) {
        try (Connection conn = sql2o.open()) {
            E item = conn
                    .createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(entityClass);
            return item;
        }
    }

    public void delete(final int id) {
        final String sql =
                "DELETE FROM " + tableName + " " +
                 "WHERE id = :id";

        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
        }
    }

}
