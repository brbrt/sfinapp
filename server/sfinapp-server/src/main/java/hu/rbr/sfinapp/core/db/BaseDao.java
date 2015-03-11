package hu.rbr.sfinapp.core.db;

import org.sql2o.Sql2o;

public class BaseDao<E> {

    protected DbProvider dbProvider;
    protected Sql2o sql2o;

    public BaseDao() {
        dbProvider = new DirectDbProvider();
        sql2o = new Sql2o(dbProvider.getDataSource());
    }

}
