package hu.rbr.sfinapp.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig implements Config {

    public static final String PROPERTY_FILE = "sfinapp.properties";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String get(String property) {
        try {
            Properties properties = readProperties();
            return properties.getProperty(property);
        } catch (IOException ex) {
            logger.warn("Could not read configurations from property file!", ex);
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
