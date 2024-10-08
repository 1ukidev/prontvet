package prontvet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import prontvet.database.UsuarioService;
import prontvet.entities.UsuarioEntity;

public class UsuarioController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Override
    public void register(Javalin app) {
        app.post("/usuario", ctx -> {
            UsuarioEntity usuario = ctx.bodyAsClass(UsuarioEntity.class);

            usuario = UsuarioService.save(usuario);

            if (usuario.getId() != null) {
                ctx.status(HttpStatus.CREATED.getCode());
                log.info("Usuário salvo: {}", usuario);
            } else {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
                log.error("Erro ao salvar usuário: {}", usuario);
            }
        });
    }

    private UsuarioController() {}

    public static final UsuarioController instance = new UsuarioController();

    public static UsuarioController getInstance() {
        return instance;
    }
}
