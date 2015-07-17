package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;
import hu.rbr.sfinapp.core.version.VersionedOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public Account create(@Valid @NotNull Account account) {
        return accountDao.create(account);
    }

    @VersionedOperation(VERSION_KEY)
    public Account update(int id, @Valid @NotNull Account account) {
        return accountDao.update(id, account);
    }

    @VersionedOperation(VERSION_KEY)
    public void delete(int id) {
        accountDao.delete(id);
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(VERSION_KEY);
    }

}

