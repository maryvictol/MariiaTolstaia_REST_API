package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private static Properties currentProperties = new Properties();
    private static String propertiesFileName = "src/test/resources/test.properties";


    private static Properties getCurrentProperties() throws IOException {
        FileInputStream in = new FileInputStream(propertiesFileName);
        currentProperties.load(in);
        in.close();
        return currentProperties;
    }

    public static String getProperty(String propertyKey) throws IOException {
        if (!currentProperties.containsKey(propertyKey)) {
            currentProperties = getCurrentProperties();
        }
        return currentProperties.getProperty(propertyKey, null);
    }
}
