package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.transaction.list.TransactionListDao;
import hu.rbr.sfinapp.transaction.list.TransactionListItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.List;

import static hu.rbr.sfinapp.transaction.TransactionType.*;

@Singleton
public class TransactionService extends BaseService {

    private final TransactionDao transactionDao;
    private final TransactionListDao transactionListDao;

    @Inject
    public TransactionService(TransactionDao transactionDao, TransactionListDao transactionListDao) {
        this.transactionDao = transactionDao;
        this.transactionListDao = transactionListDao;
    }

    public List<TransactionListItem> getAll() {
        List<TransactionListItem> transactions = transactionListDao.getAll();

        for (TransactionListItem transaction : transactions) {
            postProcess(transaction);
        }

        return transactions;
    }

    public Transaction get(int id) {
        Transaction transaction = transactionDao.get(id);
        postProcess(transaction);
        return transaction;
    }

    public Transaction create(@Valid Transaction transaction) {
        preProcess(transaction);
        return transactionDao.create(transaction);
    }

    public void createBatch(@Valid List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            preProcess(transaction);
        }

        transactionDao.createBatch(transactions);
    }

    public Transaction update(int id, @Valid Transaction transaction) {
        preProcess(transaction);
        return transactionDao.update(id, transaction);
    }

    public void delete(int id) {
        transactionDao.delete(id);
    }

    private void preProcess(Transaction transaction) {
        correctAmountBasedOnType(transaction);
    }

    private void correctAmountBasedOnType(Transaction transaction) {
        if ((transaction.type == Expense && transaction.amount > 0) ||
            (transaction.type == Income && transaction.amount < 0) ||
            (transaction.type == Transfer && transaction.amount < 0)) {

            transaction.amount *= -1;
        }
    }

    private void postProcess(Transaction transaction) {
        if (transaction == null) {
            return;
        }

        transaction.type = calculateTransactionType(transaction);
    }

    private TransactionType calculateTransactionType(Transaction transaction) {
        if (transaction.toAccountId != null) {
            return Transfer;
        }

        if (transaction.amount > 0) {
            return Income;
        }

        return Expense;
    }

}
