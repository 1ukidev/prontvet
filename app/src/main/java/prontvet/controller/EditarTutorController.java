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
import prontvet.model.EditarTutorModel;
import prontvet.table.TutorEntity;

public class EditarTutorController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnEditar;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    private EditarTutorModel model = new EditarTutorModel();

    @FXML
    void editar(ActionEvent event) {
        if (validateModel()) {
            TutorEntity tutorEntity = new TutorEntity(
                model.nome, model.telefone, model.endereco
            );
            tutorEntity.setId(model.id);

            if (TutorDAO.getInstance().update(tutorEntity)) {
                Util.showSuccess("Tutor editado com sucesso!");
                Log.debug("Tutor editado com sucesso!");
                ((Stage) borderPane.getScene().getWindow()).close();
            } else {
                Util.showError("Erro ao editado tutor!");
                Log.error("Erro ao editado tutor!");
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
