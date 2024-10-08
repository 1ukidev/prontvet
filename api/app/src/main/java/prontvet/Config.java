package prontvet;

import java.util.Properties;

public class Config {
    private Integer port;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    /**
     * Carrega as configurações do arquivo de propriedades.
     * 
     * @return Configurações carregadas.
     */
    private Config load() {
        Properties prop = new Properties();

        try {
            prop.load(Config.class.getResourceAsStream("/prontvet.properties"));
            this.port = Integer.parseInt(prop.getProperty("port"));
            this.dbUrl = prop.getProperty("db.url");
            this.dbUser = prop.getProperty("db.user");
            this.dbPassword = prop.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public Integer getPort() {
        return port;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    private Config() {}

    private static final Config instance = new Config().load();

    public static Config getInstance() {
        return instance;
    }
}
