package hu.rbr.sfinapp.transaction.list;

import com.google.common.base.Strings;
import hu.rbr.sfinapp.core.db.BaseDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TransactionListDao extends BaseDao<TransactionListItem> {

    @Inject
    public TransactionListDao(Sql2o sql2o) {
        super(sql2o, "transaction_list", TransactionListItem.class);
    }

    public List<TransactionListItem> getAll(TransactionListFilter filter) {
        String sql = buildGetAllSql(filter);

        try (Connection conn = sql2o.open()) {
            return conn
                    .createQuery(sql)
                    .bind(filter)
                    .executeAndFetch(TransactionListItem.class);
        }
    }

    private String buildGetAllSql(TransactionListFilter filter) {
        String sql = "SELECT * FROM transaction_list WHERE 1 = 1 ";

        if (filter.from != null) {
            sql += " AND date >= :from ";
        }
        if (filter.to != null) {
            sql += " AND date <= :to ";
        }
        if (!Strings.isNullOrEmpty(filter.description)) {
            filter.description = wrapInWildcards(filter.description);
            sql += " AND LOWER(description) LIKE LOWER(:description) ";
        }
        sql += " ORDER BY date DESC";

        return sql;
    }

    public List<String> getAllDescriptions() {
        final String sql =
                "SELECT DISTINCT description" +
                "  FROM transactions";

        try (Connection conn = sql2o.open()) {
            return conn
                    .createQuery(sql)
                    .executeAndFetch(String.class);
        }
    }

}
