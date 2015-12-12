package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.account.Account;
import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.account.AccountQueryService;
import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;
import hu.rbr.sfinapp.tag.TagCommandService;
import hu.rbr.sfinapp.transaction.list.TransactionListDao;
import hu.rbr.sfinapp.transaction.list.TransactionListFilter;
import hu.rbr.sfinapp.transaction.list.TransactionListItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hu.rbr.sfinapp.transaction.TransactionType.*;

@Singleton
public class TransactionQueryService extends BaseService implements Versioned {

    public static final String VERSION_KEY = "transaction";

    private final TransactionDao transactionDao;
    private final TransactionListDao transactionListDao;
    private final AccountQueryService accountQueryService;
    private final VersionStore versionStore;

    @Inject
    public TransactionQueryService(TransactionDao transactionDao,
                                   TransactionListDao transactionListDao,
                                   AccountQueryService accountQueryService,
                                   VersionStore versionStore) {
        this.transactionDao = transactionDao;
        this.transactionListDao = transactionListDao;
        this.accountQueryService = accountQueryService;
        this.versionStore = versionStore;
    }

    public List<TransactionListItem> getAll(@Valid @NotNull TransactionListFilter filter) {
        List<TransactionListItem> transactions = transactionListDao.getAll(filter);

        transactions.forEach(this::postProcess);

        return transactions;
    }

    public List<String> getAllDescriptions() {
        return transactionListDao.getAllDescriptions();
    }

    public Transaction get(int id) {
        Transaction transaction = transactionDao.get(id);
        postProcess(transaction);
        return transaction;
    }

    public Transaction skeleton() {
        Transaction skeleton = new Transaction();

        skeleton.date = new Date();
        skeleton.type = Expense;
        skeleton.tagIds = new ArrayList<>();

        List<Account> accounts = accountQueryService.getAll();
        if (!accounts.isEmpty()) {
            skeleton.accountId = accounts.get(0).id;
        }

        return skeleton;
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

    @Override
    public long getVersion() {
        return versionStore.getVersion(VERSION_KEY);
    }

    public long getListVersion() {
        return versionStore.getVersion(VERSION_KEY, AccountCommandService.VERSION_KEY, TagCommandService.VERSION_KEY);
    }

}
