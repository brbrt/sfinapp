package hu.brbrt.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@Validated
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    public Account get(int id) {
        return accountRepository.get(id);
    }

    public int create(@NotNull @Valid Account account) {
        return accountRepository.create(account);
    }

    public void update(@NotNull @Valid Account account) {
        accountRepository.update(account);
    }

    public void delete(int id) {
        accountRepository.delete(id);
    }

}
