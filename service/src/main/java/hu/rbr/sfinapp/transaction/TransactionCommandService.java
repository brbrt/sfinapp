package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.version.VersionedOperation;
import hu.rbr.sfinapp.transaction.command.CreateTransactionsCommand;
import hu.rbr.sfinapp.transaction.command.DeleteTransactionCommand;
import hu.rbr.sfinapp.transaction.command.UpdateTransactionCommand;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static hu.rbr.sfinapp.transaction.TransactionItemConverter.convertToTransaction;

@Singleton
public class TransactionCommandService extends BaseService {

    public static final String VERSION_KEY = "transaction";

    private final TransactionDao transactionDao;

    @Inject
    public TransactionCommandService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @VersionedOperation(VERSION_KEY)
    public void create(CreateTransactionsCommand command) {
        List<Transaction> transactions = command.transactions
                .stream()
                .map(TransactionItemConverter::convertToTransaction)
                .collect(Collectors.toList());

        transactionDao.createBatch(transactions);
    }

    @VersionedOperation(VERSION_KEY)
    public Transaction update(UpdateTransactionCommand command) {
        Transaction transaction = convertToTransaction(command.transaction);
        return transactionDao.update(command.id, transaction);
    }

    @VersionedOperation(VERSION_KEY)
    public void delete(DeleteTransactionCommand command) {
        transactionDao.delete(command.id);
    }

}
