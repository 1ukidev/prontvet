package prontvet.controllers;

import io.javalin.Javalin;

public interface Controller {
    /**
     * Registra as rotas do controller no Javalin.
     * 
     * @param app Instância do Javalin.
     */
    public void register(Javalin app);
}
