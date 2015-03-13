package hu.rbr.sfinapp.core.config;

public interface Config {

    String get(String property);

    int getInt(String property);

}
