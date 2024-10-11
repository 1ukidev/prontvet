package prontvet;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import prontvet.controllers.RootController;
import prontvet.controllers.UsuarioController;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // Inicializa as configurações da aplicação.
        Config.load();

        // Cria uma instância do Javalin e configura as rotas da API.
        Javalin app = Javalin.create(config -> {
            config.useVirtualThreads = true;
            config.showJavalinBanner = false;

            config.router.apiBuilder(() -> {
                path("/", () -> {
                    get(RootController::hello);
                });
                path("/usuario", () -> {
                    get(UsuarioController::hello);
                    post(UsuarioController::register);
                });
            });
        });

        // Inicia o servidor na porta especificada.
        app.start(Config.port);

        // Exibe uma mensagem de sucesso.
        Util.clearScreen();
        Util.printAsciiArt();
        log.info("Servidor iniciado com sucesso na porta {}!", Config.port);
    }
}
