package hu.rbr.sfinapp.core.version;

public interface VersionStore {

    long getVersion(String key);
    long getVersion(String... keys);
    void incrementVersion(String key);

}
