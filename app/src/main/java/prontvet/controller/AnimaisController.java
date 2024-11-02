package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import prontvet.Util;

public class AnimaisController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrarAnimal;

    @FXML
    private Button btnListarAnimais;

    @FXML
    void openCadastrarAnimal(ActionEvent event) {
        Util.openView("CadastrarAnimal", "Cadastrar animal");
    }

    @FXML
    void openListarAnimais(ActionEvent event) {
        Util.openView("ListaAnimais", "Lista de animais");
    }

}
