package prontvet;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

@FunctionalInterface
public interface Config {
    void run(Stage stage, FXMLLoader loader);
}
