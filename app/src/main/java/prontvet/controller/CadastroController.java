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
import prontvet.table.UsuarioEntity;

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
        if (validateModel()) {
            UsuarioEntity usuario = new UsuarioEntity(model.email, model.senha);
            UsuarioDAO.getInstance().save(usuario);

            if (usuario.getId() != null) {
                Util.showSuccess("Usuário cadastrado com sucesso!");
                Log.debug("Usuário cadastrado com sucesso!");
                ((Stage) borderPane.getScene().getWindow()).close();
            } else {
                Util.showError("Erro ao cadastrar usuário!");
                Log.error("Erro ao cadastrar usuário!");
            }   
        }
    }

    private boolean validateModel() {
        if (model.email == null || model.email.isEmpty()) {
            Util.showError("O e-mail precisa ser preenchido!");
            return false;
        }
        if (model.senha == null || model.senha.isEmpty()) {
            Util.showError("A senha precisa ser preenchida!");
            return false;
        }
        if (model.repitaSenha == null || model.repitaSenha.isEmpty()) {
            Util.showError("A senha precisa ser repetida!");
            return false;
        }
        if (!model.senha.equals(model.repitaSenha)) {
            Util.showError("As senhas não conferem!");
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            model.email = newValue;
        });
        txtSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            model.senha = newValue;
        });
        txtRepitaSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            model.repitaSenha = newValue;
        });
    }

}
