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
        if (model.validate()) {
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

    @FXML
    void initialize() {
        addBindings();
    }

    private void addBindings() {
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
