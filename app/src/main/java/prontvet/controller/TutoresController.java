package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import prontvet.Util;

public class TutoresController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrarTutor;

    @FXML
    private Button btnListarTutores;

    @FXML
    void openCadastrarTutor(ActionEvent event) {
        Util.openView("CadastrarTutor", "Cadastrar tutor");
    }

    @FXML
    void openListarTutores(ActionEvent event) {
        Util.openView("ListaTutores", "Lista de tutores", (stage, loader) -> {
            stage.setResizable(true);
            stage.setMinWidth(900);
            stage.setMinHeight(500);
        });
    }

}
