package hu.rbr.sfinapp.account;

import hu.rbr.sfinapp.IntegrationTestBase;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Ignore
public class AccountTest extends IntegrationTestBase {

    private static class AccountListType extends GenericType<List<Account>> {}

    @Test
    public void addAndQuery() throws Exception {
        List<Account> accounts = webTarget("accounts")
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(0));


        Account newAccount = new Account();
        newAccount.name = "ACC1";
        newAccount.description = "DSCR";

        webTarget("accounts")
            .request()
            .post(Entity.json(newAccount));


        accounts = webTarget("accounts")
                .request()
                .get(new AccountListType());

        assertThat(accounts.size(), equalTo(1));
        assertThat(accounts.get(0).id, notNullValue());
        assertThat(accounts.get(0).name, equalTo("ACC1"));
        assertThat(accounts.get(0).description, equalTo("DSCR"));
        assertThat(accounts.get(0).technical, equalTo(false));
    }

}
