package hu.brbrt.account;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = GET)
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public Account get(@PathVariable("id") int id) {
        return accountService.get(id);
    }

    @RequestMapping(method = POST)
    public int create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @RequestMapping(method = PUT, path = "/{id}")
    public void update(@RequestBody Account account) {
        accountService.update(account);
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        accountService.delete(id);
    }

}
