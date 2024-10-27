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
    private Button btnListarAnimais;

    @FXML
    private Button btnListarTutores;

    @FXML
    void openCadastrarAnimal(ActionEvent event) {
        Util.openView("Animal", "Cadastrar animal");
    }

    @FXML
    void openCadastrarTutor(ActionEvent event) {
        Util.openView("Tutor", "Cadastrar tutor");
    }

    @FXML
    void openListarAnimais(ActionEvent event) {
        // TODO
    }

    @FXML
    void openListarTutores(ActionEvent event) {
        // TODO
    }

}
