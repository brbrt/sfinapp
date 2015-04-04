package hu.rbr.sfinapp.transaction.list;

import hu.rbr.sfinapp.core.db.BaseDao;

import java.util.List;

public class TransactionListDao extends BaseDao<TransactionListItem> {

    public TransactionListDao() {
        super("transaction_list", TransactionListItem.class);
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
