package prontvet.controller;

import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import prontvet.Util;
import prontvet.dao.OthersDAO;

public class AppController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnAnimais;

    @FXML
    private Button btnTutores;

    @FXML
    private Label label;

    @FXML
    void openAnimais(ActionEvent event) {
        Util.openView("Animais", "Animais");
    }

    @FXML
    void openTutores(ActionEvent event) {
        Util.openView("Tutores", "Tutores");
    }

    @FXML
    void initialize() {
        // Verificar se o banco de dados está funcionando.
        OthersDAO.test();

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
