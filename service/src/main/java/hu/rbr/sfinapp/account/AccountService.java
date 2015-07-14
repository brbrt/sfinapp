package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class AccountService extends BaseService implements Versioned {

    private final AtomicInteger version = new AtomicInteger();

    private final AccountDao accountDao;

    @Inject
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
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
    public int getVersion() {
        return version.get();
    }

    private void incrementVersion() {
        version.incrementAndGet();
    }

}

