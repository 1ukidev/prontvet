package prontvet;

public class Util {
    private static final String[] asciiArt = {
        " ____                  _             _        ",
        "|  _ \\ _ __ ___  _ __ | |___   _____| |_     ",
        "| |_) | '__/ _ \\| '_ \\| __\\ \\ / / _ \\ __|",
        "|  __/| | | (_) | | | | |_ \\ V /  __/ |_     ",
        "|_|   |_|  \\___/|_| |_|\\__| \\_/ \\___|\\__|",
        "                                              "
    };

    /**
     * Imprime a logo ASCII da aplicação.
     */
    public static void printAsciiArt() {
        for (String line : asciiArt) {
            System.out.println(line);
        }
    }

    /**
     * Limpa a tela do terminal.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
