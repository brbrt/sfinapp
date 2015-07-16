package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.IntegrationTestBase;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_MODIFIED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AccountIT extends IntegrationTestBase {

    private static class AccountListType extends GenericType<List<Account>> {}

    @Test
    public void addAndQuery() throws Exception {
        List<Account> accounts = accountWebTarget()
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(0));

        Account acc1 = createAccount("1-name", "1-desc", false);
        Account acc2 = createAccount("2-name", "2-desc", true);

        accounts = accountWebTarget()
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(2));
        assertThat(accounts.get(0), new AccountMatcher(acc1));
        assertThat(accounts.get(1), new AccountMatcher(acc2));


        Integer acc1Id = accounts.get(0).id;

        Account account = accountWebTarget()
                .path(acc1Id.toString())
                .request()
                .get(Account.class);

        assertThat(account.id, equalTo(acc1Id));
        assertThat(account, new AccountMatcher(acc1));
    }

    @Test
    public void etagVersion() throws Exception {
        createAccount("1-name", "1-desc", false);

        Response firstResponse = accountWebTarget()
                .request()
                .get();

        assertThat(firstResponse.getStatus(), equalTo(OK.getStatusCode()));


        Response secondResponse = accountWebTarget()
                .request()
                .header("If-None-Match", firstResponse.getEntityTag())
                .get();

        assertThat(secondResponse.getStatus(), equalTo(NOT_MODIFIED.getStatusCode()));


        createAccount("2-name", "2-desc", true);

        Response thirdResponse = accountWebTarget()
                .request()
                .header("If-None-Match", firstResponse.getEntityTag())
                .get();

        assertThat(thirdResponse.getStatus(), equalTo(OK.getStatusCode()));
    }

    private WebTarget accountWebTarget() {
        return webTarget("accounts");
    }

    private Account createAccount(String name, String description, boolean technical) {
        Account newAccount = new Account();
        newAccount.name = name;
        newAccount.description = description;
        newAccount.technical = technical;

        accountWebTarget()
            .request()
            .post(Entity.json(newAccount));

        return newAccount;
    }

}
