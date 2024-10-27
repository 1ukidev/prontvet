package prontvet.controller;

import java.util.regex.PatternSyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.Util;
import prontvet.model.TutorModel;

public class TutorController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    private TutorModel model = new TutorModel();

    @FXML
    void cadastrar(ActionEvent event) {
        if (validateModel()) {
            Log.debug("Cadastrando...");
            Log.debug("Nome: " + model.nome);
            Log.debug("Telefone: " + model.telefone);
            Log.debug("Endereço: " + model.endereco);
            Log.debug("Cadastrado com sucesso!");
            Util.showSuccess("Tutor cadastrado com sucesso!");
        }
    }

    private boolean validateModel() {
        if (model.nome == null || model.nome.isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (model.telefone == null || model.telefone.isEmpty()) {
            Util.showError("O telefone precisa ser preenchido!");
            return false;
        }
        if (model.endereco == null || model.endereco.isEmpty()) {
            Util.showError("O endereço precisa ser preenchido!");
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            model.nome = newValue;
        });
        txtTelefone.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.matches("\\d*")) {
                    model.telefone = newValue;
                } else {
                    txtTelefone.setText(oldValue);
                }
            } catch (PatternSyntaxException e) {}
        });
        txtEndereco.textProperty().addListener((observable, oldValue, newValue) -> {
            model.endereco = newValue;
        });
    }

}
