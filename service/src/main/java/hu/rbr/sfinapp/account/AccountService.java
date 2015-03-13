package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.core.service.BaseService;

import java.util.List;


public class AccountService extends BaseService {

    private AccountDao accountDao = new AccountDao();

	public List<Account> getAllAccounts() {
		return accountDao.getAllAccounts();
	}
	
	public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
	}
	
	public Account createAccount(Account acc) {
		return accountDao.createAccount(acc);
	}
	
	public void updateAccount(int id, Account acc) {
		accountDao.updateAccount(id, acc);
	}
	
	public void deleteAccount(int id) {
		accountDao.deleteAccount(id);
	}
}
