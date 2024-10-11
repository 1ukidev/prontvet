package prontvet.controllers;

import io.javalin.http.Context;

public class RootController {
    public static void hello(Context ctx) {
        ctx.result("Ola, mundo!");
    }
}
