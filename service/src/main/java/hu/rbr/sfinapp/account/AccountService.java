package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.db.BaseDao;
import hu.rbr.sfinapp.core.service.BaseService;

import java.util.List;


public class AccountService extends BaseService<Account> {

    private final AccountDao accountDao = new AccountDao();

    @Override
    protected BaseDao<Account> getDao() {
        return accountDao;
    }

}
