package hu.brbrt.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "account")
@Validated
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = GET)
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public Account get(@PathVariable("id") int id) {
        return accountRepository.get(id);
    }

    @RequestMapping(method = POST)
    public void create(@RequestBody @NotNull @Valid Account account) {
        accountRepository.create(account);
    }

    @RequestMapping(method = PUT)
    public void update(@RequestBody @NotNull @Valid Account account) {
        accountRepository.update(account);
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        accountRepository.delete(id);
    }

}
