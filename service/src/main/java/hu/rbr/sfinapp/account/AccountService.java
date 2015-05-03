package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.List;

@Singleton
public class AccountService extends BaseService {

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

    public Account create(@Valid Account account) {
        return accountDao.create(account);
    }

    public Account update(int id, @Valid Account account) {
        return accountDao.update(id, account);
    }

    public void delete(int id) {
        accountDao.delete(id);
    }

}

