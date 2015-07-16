package hu.rbr.sfinapp.core.version;

public interface VersionStore {

    long getVersion(String key);
    void incrementVersion(String key);

}
