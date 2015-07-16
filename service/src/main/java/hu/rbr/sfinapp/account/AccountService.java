package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;

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

    public Account create(@Valid @NotNull Account account) {
        Account created = accountDao.create(account);
        incrementVersion();
        return created;
    }

    public Account update(int id, @Valid @NotNull Account account) {
        Account updated = accountDao.update(id, account);
        incrementVersion();
        return updated;
    }

    public void delete(int id) {
        accountDao.delete(id);
        incrementVersion();
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(VERSION_KEY);
    }

    private void incrementVersion() {
        versionStore.incrementVersion(VERSION_KEY);
    }

}

