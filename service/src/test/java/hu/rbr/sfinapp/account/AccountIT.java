package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.IntegrationTestBase;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountIT extends IntegrationTestBase {

    private static class AccountListType extends GenericType<List<Account>> {}

    @Test
    public void addAndQuery() throws Exception {
        List<Account> accounts = webTarget("accounts")
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(0));

        Account acc1 = createAccount("1-name", "1-desc", false);
        Account acc2 = createAccount("2-name", "2-desc", true);

        accounts = webTarget("accounts")
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(2));
        assertThat(accounts.get(0), new AccountMatcher(acc1));
        assertThat(accounts.get(1), new AccountMatcher(acc2));


        Integer acc1Id = accounts.get(0).id;

        Account account = webTarget("accounts")
                .path(acc1Id.toString())
                .request()
                .get(Account.class);

        assertThat(account.id, equalTo(acc1Id));
        assertThat(account, new AccountMatcher(acc1));
    }

    private Account createAccount(String name, String description, boolean technical) {
        Account newAccount = new Account();
        newAccount.name = name;
        newAccount.description = description;
        newAccount.technical = technical;

        webTarget("accounts")
            .request()
            .post(Entity.json(newAccount));

        return newAccount;
    }

}
