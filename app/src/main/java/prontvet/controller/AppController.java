package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import prontvet.Util;

public class AppController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrarAnimal;

    @FXML
    private Button btnCadastrarTutor;

    @FXML
    void openCadastrarAnimal(ActionEvent event) {
        Util.openView("Animal", "Cadastrar animal", e -> {
            e.setResizable(false);
        });
    }

    @FXML
    void openCadastrarTutor(ActionEvent event) {
        Util.openView("Tutor", "Cadastrar tutor", e -> {
            e.setResizable(false);
        });
    }

}
