package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.UsuarioDAO;
import prontvet.model.CadastroModel;

public class CadastroController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private PasswordField txtRepitaSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    private CadastroModel model = new CadastroModel();

    @FXML
    void cadastrar(ActionEvent event) {
        if (model.validate()) {
            UsuarioDAO.getInstance().save(model.usuarioEntity);

            if (model.usuarioEntity.getId() != null) {
                Util.showSuccess("Usuário cadastrado com sucesso!");
                Log.debug("Usuário cadastrado com sucesso!");
                ((Stage) borderPane.getScene().getWindow()).close();
            } else {
                Util.showError("Erro ao cadastrar usuário!");
                Log.error("Erro ao cadastrar usuário!");
            }   
        }
    }

    @FXML
    void initialize() {
        addBindings();
    }

    private void addBindings() {
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            model.usuarioEntity.setEmail(newValue);
        });
        txtSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            model.usuarioEntity.setSenha(newValue);
        });
        txtRepitaSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            model.repitaSenha = newValue;
        });
    }

}
