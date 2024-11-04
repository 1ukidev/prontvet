package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.TutorDAO;
import prontvet.model.TutorModel;
import prontvet.table.TutorEntity;

public class CadastrarTutorController {

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
            TutorEntity tutorTable = new TutorEntity(
                model.nome, model.telefone, model.endereco
            );
            TutorDAO.getInstance().save(tutorTable);

            if (tutorTable.getId() != null) {
                Util.showSuccess("Tutor cadastrado com sucesso!");
                Log.debug("Tutor cadastrado com sucesso!");
                borderPane.getScene().getWindow().hide();
            } else {
                Util.showError("Erro ao cadastrar tutor!");
                Log.error("Erro ao cadastrar tutor!");
            }
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
            if (newValue.matches("\\d*") && newValue.length() <= 11) {
                model.telefone = newValue.isEmpty() ? "" : newValue;
            } else {
                txtTelefone.setText(oldValue);
            }
        });
        txtEndereco.textProperty().addListener((observable, oldValue, newValue) -> {
            model.endereco = newValue;
        });
    }

}
