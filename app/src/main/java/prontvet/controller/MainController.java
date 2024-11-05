package prontvet.controller;

import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import prontvet.Util;

public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnPacientes;

    @FXML
    private Button btnTutores;

    @FXML
    private Label label;

    @FXML
    void openPacientes(ActionEvent event) {
        Util.openView("Pacientes", "Pacientes");
    }

    @FXML
    void openTutores(ActionEvent event) {
        Util.openView("Tutores", "Tutores");
    }

    @FXML
    void initialize() {
        // Definir a saudação de acordo com o horário.
        int hours = LocalTime.now().getHour();
        if (hours >= 6 && hours < 12) {
            label.setText("Bom dia!");
        } else if (hours >= 12 && hours < 18) {
            label.setText("Boa tarde!");
        } else {
            label.setText("Boa noite!");
        }
    }

}
