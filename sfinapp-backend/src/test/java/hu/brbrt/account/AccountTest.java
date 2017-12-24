package hu.brbrt.account;

import hu.brbrt.TestBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountTest extends TestBase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private AccountController accountController;

    @Test
    public void crudAccount() {
        int id = accountController.create(new Account()
                .setName("A1")
                .setDescription("A1D")
                .setTechnical(false)
        );

        List<Account> accounts = accountController.getAll();
        assertThat(accounts, hasSize(1));
        Account account = accounts.get(0);
        assertThat(account.getId(), is(id));
        assertThat(account.getName(), is("A1"));
        assertThat(account.getDescription(), is("A1D"));
        assertThat(account.isTechnical(), is(false));

        accountController.update(account
                .setName("A1-UPD")
                .setDescription("A1D-UPD")
                .setTechnical(true)
        );

        Account updatedAccount = accountController.get(id);
        assertThat(updatedAccount.getId(), is(id));
        assertThat(updatedAccount.getName(), is("A1-UPD"));
        assertThat(updatedAccount.getDescription(), is("A1D-UPD"));
        assertThat(updatedAccount.isTechnical(), is(true));

        accountController.delete(id);

        assertThat(accountController.getAll(), empty());
    }

    @Test
    public void validation() {
        exception.expect(ConstraintViolationException.class);
        accountController.create(new Account());
    }

}
