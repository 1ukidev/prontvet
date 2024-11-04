package prontvet;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Util {
    public static void openView(String view, String title, Stage stage) {
        try {
            Parent root = FXMLLoader.load(Util.class.getResource("/view/" + view + ".fxml"));
            Scene scene = new Scene(root);

            Stage currentStage = null;
            if (stage != null) {
                currentStage = stage;
            } else {
                currentStage = new Stage();
            }

            currentStage.getIcons().add(logo);
            currentStage.setScene(scene);
            currentStage.setTitle(title);
            currentStage.setResizable(false);
            currentStage.centerOnScreen();
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openView(String view, String title) {
        openView(view, title, null);
    }

    public static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.show();
    }

    public static void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.show();
    }

    private static Image logo = new Image(Util.class.getResourceAsStream("/logo.png"));
}
