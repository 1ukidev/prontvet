package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.TutorDAO;
import prontvet.model.CadastrarTutorModel;

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

    private CadastrarTutorModel model = new CadastrarTutorModel();

    @FXML
    void cadastrar(ActionEvent event) {
        if (validateModel()) {
            TutorDAO.getInstance().save(model.tutorEntity);

            if (model.tutorEntity.getId() != null) {
                Util.showSuccess("Tutor cadastrado com sucesso!");
                Log.debug("Tutor cadastrado com sucesso!");
                ((Stage) borderPane.getScene().getWindow()).close();
            } else {
                Util.showError("Erro ao cadastrar tutor!");
                Log.error("Erro ao cadastrar tutor!");
            }
        }
    }

    private boolean validateModel() {
        if (model.tutorEntity.getNome() == null || model.tutorEntity.getNome().isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (model.tutorEntity.getTelefone() == null || model.tutorEntity.getTelefone().isEmpty()) {
            Util.showError("O telefone precisa ser preenchido!");
            return false;
        }
        if (model.tutorEntity.getEndereco() == null || model.tutorEntity.getEndereco().isEmpty()) {
            Util.showError("O endereço precisa ser preenchido!");
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            model.tutorEntity.setNome(newValue);
        });
        txtTelefone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*") && newValue.length() <= 11) {
                model.tutorEntity.setTelefone(newValue.isEmpty() ? "" : newValue);
            } else {
                txtTelefone.setText(oldValue);
            }
        });
        txtEndereco.textProperty().addListener((observable, oldValue, newValue) -> {
            model.tutorEntity.setEndereco(newValue);
        });
    }

}
