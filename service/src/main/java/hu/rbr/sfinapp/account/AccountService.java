package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;

import java.util.List;


public class AccountService extends BaseService {

    private final AccountDao accountDao = new AccountDao();

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public Account get(int id) {
        return accountDao.get(id);
    }

    public Account create(Account account) {
        return accountDao.create(account);
    }

    public Account update(int id, Account account) {
        return accountDao.update(id, account);
    }

    public void delete(int id) {
        accountDao.delete(id);
    }

}

