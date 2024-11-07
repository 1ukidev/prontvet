package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import prontvet.Util;

public class PacientesController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrarPaciente;

    @FXML
    private Button btnListaDePacientes;

    @FXML
    void openCadastrarPaciente(ActionEvent event) {
        Util.openView("CadastrarPaciente", "Cadastrar paciente");
    }

    @FXML
    void openListaDePacientes(ActionEvent event) {
        Util.openView("ListaPacientes", "Lista de pacientes", stage -> {
            stage.setResizable(true);
            stage.setMinWidth(900);
            stage.setMinHeight(500);
        });
    }

}
