package hu.rbr.sfinapp.core;

public interface Config {

    String get(String property);

    int getInt(String property);

}
