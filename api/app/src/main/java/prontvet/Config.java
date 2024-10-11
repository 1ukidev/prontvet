package prontvet;

import java.util.Properties;

public class Config {
    public static Integer port;
    public static String dbUrl;
    public static String dbUser;
    public static String dbPassword;

    /**
     * Carrega as configurações do arquivo de propriedades.
     */
    static void load() {
        Properties prop = new Properties();

        try {
            prop.load(Config.class.getResourceAsStream("/prontvet.properties"));
            port = Integer.parseInt(prop.getProperty("port"));
            dbUrl = prop.getProperty("db.url");
            dbUser = prop.getProperty("db.user");
            dbPassword = prop.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
