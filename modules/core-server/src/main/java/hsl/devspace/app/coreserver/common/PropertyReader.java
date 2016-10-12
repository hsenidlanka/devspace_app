package hsl.devspace.app.coreserver.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Kasun Dinesh on 10/12/16.
 * This class works as a any property file reader.
 */
public class PropertyReader {

    private final String fileName;
    private static final Logger log = LoggerFactory.getLogger(PropertyReader.class);

    /**
     * Default constructor of PropertyReader class
     * @param fileName Name of the file where settings saved
     */
    public PropertyReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method will be used to read the system.properties file
     * @return Properties object with the values from the given settings file
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input;
        input = classLoader.getResourceAsStream(fileName);  // Load file data to the stream.
        try {
            properties.load(input); // Load stream data to the Property object.
        } catch (IOException ex) {
            log.error("Error while loading properties. ", ex);
        } finally {
            try {
                input.close();  // Closing the stream.
            } catch (IOException ex) {
                log.error("Error while closing the property stream. ", ex);
            }
        }
        return properties;
    }

    /**
     * @param property Setting name need to be read from the properties file
     * @return Value of the given property(setting)
     */
    public String readProperty(String property) {
        Properties prop;
        prop = loadProperties();
        return prop.getProperty(property);
    }
}