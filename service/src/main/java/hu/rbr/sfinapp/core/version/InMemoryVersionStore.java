package hu.rbr.sfinapp.core.version;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryVersionStore implements VersionStore {

    private final Map<String, Long> store = new ConcurrentHashMap<>();

    @Override
    public long getVersion(String key) {
        if (!store.containsKey(key)) {
            incrementVersion(key);
        }

        return store.get(key);
    }

    @Override
    public void incrementVersion(String key) {
        Long version = store.get(key);

        if (version == null) {
            version = System.currentTimeMillis();
        }

        version++;

        store.put(key, version);
    }

}
