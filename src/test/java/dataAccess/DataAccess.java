package dataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataAccess {
    private Properties properties;
    private static DataAccess configFileReader;

    private DataAccess() {
        BufferedReader reader;
        String propertyFilePath = "Config//config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties not found at " + propertyFilePath);
        }
    }

    public static DataAccess getInstance() {
        if (configFileReader == null) {
            configFileReader = new DataAccess();
        }
        return configFileReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("BaseUrl");
        if (baseUrl != null)
            return baseUrl;
        else
            throw new RuntimeException("BaseUrl not specified in the Configuration.properties file.");
    }

    public String getToken() {
        String token = properties.getProperty("token");
        if (token != null)
            return token;
        else
            throw new RuntimeException("token not specified in the Configuration.properties file.");
    }
    public String getLogLocation() {
        String token = properties.getProperty("storeLogsHere");
        if (token != null)
            return token;
        else
            throw new RuntimeException("storeLogsHere not specified in the Configuration.properties file.");
    }
}
