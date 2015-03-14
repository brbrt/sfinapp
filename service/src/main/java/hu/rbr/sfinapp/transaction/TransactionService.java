package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.db.BaseDao;
import hu.rbr.sfinapp.core.service.BaseService;

import java.util.List;

import static hu.rbr.sfinapp.transaction.TransactionType.Expense;
import static hu.rbr.sfinapp.transaction.TransactionType.Income;

public class TransactionService extends BaseService<Transaction> {

    private final TransactionDao transactionDao = new TransactionDao();

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = transactionDao.getAll();

        for (Transaction transaction : transactions) {
            postProcess(transaction);
        }

        return transactions;
    }

    @Override
    public Transaction get(int id) {
        Transaction transaction = transactionDao.get(id);
        return postProcess(transaction);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionDao.create(preProcess(transaction));
    }

    @Override
    public Transaction update(int id, Transaction entity) {
        return transactionDao.update(id, preProcess(entity));
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

    @Override
    protected BaseDao<Transaction> getDao() {
        return transactionDao;
    }

}
