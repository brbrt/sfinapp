package hu.rbr.sfinapp.core.version;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class InMemoryVersionStoreTest {

    private static final String KEY = "key1";

    private InMemoryVersionStore versionStore;

    @Before
    public void init() {
        versionStore = new InMemoryVersionStore();
    }

    @Test
    public void incrementAndGet() {
        long v1 = versionStore.getVersion(KEY);
        assertThat(v1, greaterThan(0L));

        versionStore.incrementVersion(KEY);

        long v2 = versionStore.getVersion(KEY);
        assertThat(v2, greaterThan(v1));

        long v2Again = versionStore.getVersion(KEY);
        assertThat(v2Again, equalTo(v2));
    }

    @Test
    public void twoKeys() {
        String otherKey = "other";

        long mainV1 = versionStore.getVersion(KEY);
        assertThat(mainV1, greaterThan(0L));

        long otherV1 = versionStore.getVersion(otherKey);

        versionStore.incrementVersion(KEY);

        long mainV2 = versionStore.getVersion(KEY);
        assertThat(mainV2, greaterThan(mainV1));

        long otherV1Again = versionStore.getVersion(otherKey);
        assertThat(otherV1Again, equalTo(otherV1));
    }

}
