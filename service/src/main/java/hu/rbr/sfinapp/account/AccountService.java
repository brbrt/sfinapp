package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.account.command.CreateAccountCommand;
import hu.rbr.sfinapp.account.command.DeleteAccountCommand;
import hu.rbr.sfinapp.account.command.UpdateAccountCommand;
import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;
import hu.rbr.sfinapp.core.version.VersionedOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AccountService extends BaseService implements Versioned {

    public static final String VERSION_KEY = "account";

    private final AccountDao accountDao;
    private final VersionStore versionStore;

    @Inject
    public AccountService(AccountDao accountDao, VersionStore versionStore) {
        this.accountDao = accountDao;
        this.versionStore = versionStore;
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public Account get(int id) {
        return accountDao.get(id);
    }

    @VersionedOperation(VERSION_KEY)
    public void create(CreateAccountCommand command) {
        Account newAccount = new Account();
        newAccount.name = command.name;
        newAccount.description = command.description;
        newAccount.technical = command.technical;

        accountDao.create(newAccount);
    }

    @VersionedOperation(VERSION_KEY)
    public void update(UpdateAccountCommand command) {
        Account account = new Account();
        account.id = command.id;
        account.name = command.name;
        account.description = command.description;
        account.technical = command.technical;

        accountDao.update(command.id, account);
    }

    @VersionedOperation(VERSION_KEY)
    public void delete(DeleteAccountCommand command) {
        accountDao.delete(command.id);
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(VERSION_KEY);
    }
}

