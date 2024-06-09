
package util;

import java.io.IOException;
import java.util.Properties;


public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("repository.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
