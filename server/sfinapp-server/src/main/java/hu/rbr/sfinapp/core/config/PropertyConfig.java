package hu.rbr.sfinapp.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig implements Config {

    public static final String PROPERTY_FILE = "sfinapp.properties";

    public String get(String property) {
        try {
            Properties properties = readProperties();
            return properties.getProperty(property);
        } catch (IOException ex) {
            // TODO proper logging
            ex.printStackTrace();
            throw new RuntimeException("Error reading config file.", ex);
        }
    }

    public int getInt(String property) {
        return Integer.parseInt(get(property));
    }

    private Properties readProperties() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
