package prontvet;

import java.util.Date;

public class Log {
    public static void debug(Object obj) {
        System.out.println("[" + new Date() + " - DEBUG] " + obj);
    }

    public static void error(Object obj) {
        System.err.println("[" + new Date() + " - ERROR] " + obj);
    }
}
