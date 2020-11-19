package dividendwriter;

import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class Config {
    private String host;
    private String login;
    private String password;
    private static Config thisObject = null;

    public String getHost() {
        return host;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private Config() {
        try {
            Properties properties = new Properties();
            String propFileName = "config.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);

            host = properties.getProperty("db.host");
            login = properties.getProperty("db.login");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getProperties() {
        if (thisObject == null) {
            thisObject = new Config();
        }

        return thisObject;
    }
}
