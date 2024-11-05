package prontvet;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Adiciona um tema ao programa.
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        // Abre a tela de login.
        Util.openView("Login", "ProntVet", primaryStage);
    }
}
