package prontvet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import prontvet.controllers.RootController;
import prontvet.controllers.UsuarioController;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // Cria uma instância do Javalin.
        Javalin app = Javalin.create();

        // Registra os controllers.
        RootController.getInstance().register(app);
        UsuarioController.getInstance().register(app);

        // Inicia o servidor na porta especificada.
        int port = Config.getInstance().getPort();
        app.start(port);

        // Exibe uma mensagem de sucesso.
        Util.clearScreen();
        Util.printAsciiArt();
        log.info("Servidor iniciado com sucesso na porta {}!", port);
    }
}
