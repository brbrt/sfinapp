package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.transaction.list.TransactionListDao;
import hu.rbr.sfinapp.transaction.list.TransactionListItem;

import java.util.List;

import static hu.rbr.sfinapp.transaction.TransactionType.Expense;
import static hu.rbr.sfinapp.transaction.TransactionType.Income;

public class TransactionService extends BaseService {

    private final TransactionDao transactionDao = new TransactionDao();
    private final TransactionListDao transactionListDao = new TransactionListDao();

    public List<TransactionListItem> getAll() {
        return transactionListDao.getAll();
    }

    public Transaction get(int id) {
        Transaction transaction = transactionDao.get(id);
        return postProcess(transaction);
    }

    public Transaction create(Transaction transaction) {
        return transactionDao.create(preProcess(transaction));
    }

    public Transaction update(int id, Transaction entity) {
        return transactionDao.update(id, preProcess(entity));
    }

    public void delete(int id) {
        transactionDao.delete(id);
    }

    private Transaction preProcess(Transaction transaction) {
        correctAmountBasedOnType(transaction);
        return transaction;
    }

    private void correctAmountBasedOnType(Transaction transaction) {
        if ((transaction.type == Expense && transaction.amount > 0) ||
            (transaction.type == Income && transaction.amount < 0)) {

            transaction.amount *= -1;
        }
    }

    private Transaction postProcess(Transaction transaction) {
        transaction.type = calculateTransactionType(transaction);
        return transaction;
    }

    private TransactionType calculateTransactionType(Transaction transaction) {
        if (transaction.amount > 0) {
            return Income;
        }

        return Expense;
    }

}
