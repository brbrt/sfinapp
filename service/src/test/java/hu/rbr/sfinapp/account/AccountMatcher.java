package hu.rbr.sfinapp.account;

import com.google.common.base.Objects;
import org.hamcrest.CustomTypeSafeMatcher;

public class AccountMatcher extends CustomTypeSafeMatcher<Account> {

    private final Account base;

    public AccountMatcher(Account base) {
        super("Account with ");
        this.base = base;
    }

    @Override
    protected boolean matchesSafely(Account account) {
        return Objects.equal(base.name, account.name) &&
                Objects.equal(base.description, account.description) &&
                Objects.equal(base.technical, account.technical);
    }

}
