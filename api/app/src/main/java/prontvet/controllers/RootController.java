package prontvet.controllers;

import io.javalin.Javalin;
import prontvet.Util;

public class RootController implements Controller {
    @Override
    public void register(Javalin app) {
        app.get("/", ctx -> {
            Util.applyUTF8(ctx);
            ctx.result("Olá, mundo!");
        });
    }

    private RootController() {}

    public static final RootController instance = new RootController();

    public static RootController getInstance() {
        return instance;
    }
}
