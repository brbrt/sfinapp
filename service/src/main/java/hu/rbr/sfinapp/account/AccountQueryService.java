package hu.rbr.sfinapp.account;

import com.google.inject.Singleton;
import hu.rbr.sfinapp.core.service.BaseService;
import hu.rbr.sfinapp.core.service.Versioned;
import hu.rbr.sfinapp.core.version.VersionStore;

import javax.inject.Inject;
import java.util.List;

@Singleton
public class AccountQueryService extends BaseService implements Versioned {

    private final AccountDao accountDao;
    private final VersionStore versionStore;

    @Inject
    public AccountQueryService(AccountDao accountDao, VersionStore versionStore) {
        this.accountDao = accountDao;
        this.versionStore = versionStore;
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public Account get(int id) {
        return accountDao.get(id);
    }

    @Override
    public long getVersion() {
        return versionStore.getVersion(AccountCommandService.VERSION_KEY);
    }

}
