package hu.rbr.sfinapp.transaction.list;

import hu.rbr.sfinapp.core.db.BaseDao;
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

    public List<TransactionListItem> getAll() {
        final String sql =
                "SELECT * " +
                "  FROM transaction_list" +
                "  ORDER BY date DESC";

        return getAll(sql);
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
