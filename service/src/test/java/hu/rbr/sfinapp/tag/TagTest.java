package hu.rbr.sfinapp.tag;

import hu.rbr.sfinapp.IntegrationTestBase;
import hu.rbr.sfinapp.account.Account;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Ignore
public class TagTest extends IntegrationTestBase {

    private static class TagListType extends GenericType<List<Tag>> {}

    @Test
    public void addAndQuery() throws Exception {
        List<Tag> accounts = webTarget("tags")
                .request()
                .get(new TagListType());

        assertThat(accounts.size(), equalTo(0));


        Account newAccount = new Account();
        newAccount.name = "TG";
        newAccount.description = "DSCR";

        webTarget("tags")
                .request()
                .post(Entity.json(newAccount));


        accounts = webTarget("tags")
                .request()
                .get(new TagListType());

        assertThat(accounts.size(), equalTo(1));
        assertThat(accounts.get(0).id, notNullValue());
        assertThat(accounts.get(0).name, equalTo("TG"));
        assertThat(accounts.get(0).description, equalTo("DSCR"));
    }

}
