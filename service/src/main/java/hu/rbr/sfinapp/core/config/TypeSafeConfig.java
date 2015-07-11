package hu.rbr.sfinapp.core.config;

import com.typesafe.config.ConfigFactory;

public class TypeSafeConfig implements Config {

    private com.typesafe.config.Config cachedConfig;

    @Override
    public String get(String property) {
        return getConfiguration().getString(property);
    }

    @Override
    public int getInt(String property) {
        return getConfiguration().getInt(property);
    }

    private com.typesafe.config.Config getConfiguration() {
        if (cachedConfig == null) {
            cachedConfig = ConfigFactory.load();
        }

        return cachedConfig;
    }

}
