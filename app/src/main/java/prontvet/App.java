package prontvet;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Adiciona um tema ao programa.
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        // Garante que o programa será encerrado ao fechar a janela principal.
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            Platform.exit();
        });

        Util.openView("App", "ProntVet", primaryStage);
    }
}
