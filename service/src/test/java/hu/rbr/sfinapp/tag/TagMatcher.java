package hu.rbr.sfinapp.tag;

import com.google.common.base.Objects;
import hu.rbr.sfinapp.account.Account;
import org.hamcrest.CustomTypeSafeMatcher;

public class TagMatcher extends CustomTypeSafeMatcher<Tag> {

    private final Tag base;

    public TagMatcher(Tag base) {
        super("Tag with " + base);
        this.base = base;
    }

    @Override
    protected boolean matchesSafely(Tag tag) {
        return Objects.equal(base.name, tag.name) &&
                Objects.equal(base.description, tag.description);
    }

}
