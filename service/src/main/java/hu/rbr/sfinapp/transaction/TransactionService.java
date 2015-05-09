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
        return postProcess(transaction);
    }

    public Transaction create(@Valid Transaction transaction) {
        return transactionDao.create(preProcess(transaction));
    }

    public void createBatch(@Valid List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            preProcess(transaction);
        }

        transactionDao.createBatch(transactions);
    }

    public Transaction update(int id, @Valid Transaction entity) {
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
            (transaction.type == Income && transaction.amount < 0) ||
            (transaction.type == Transfer && transaction.amount < 0)) {

            transaction.amount *= -1;
        }
    }

    private Transaction postProcess(Transaction transaction) {
        transaction.type = calculateTransactionType(transaction);
        return transaction;
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
