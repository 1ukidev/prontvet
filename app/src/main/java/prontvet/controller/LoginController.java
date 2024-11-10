package prontvet.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prontvet.Util;
import prontvet.dao.OthersDAO;
import prontvet.dao.UsuarioDAO;
import prontvet.model.LoginModel;
import prontvet.table.UsuarioEntity;

public class LoginController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink linkSeCadastrar;

    private LoginModel model = new LoginModel();

    @FXML
    void login(ActionEvent event) {
        if (!validateModel()) {
            return;
        }

        UsuarioEntity usuario = new UsuarioEntity(model.email, model.senha);

        if (UsuarioDAO.getInstance().find(usuario)) {
            Util.openView("Main", "ProntVet", stage -> {
                // Garantir que o programa será encerrado ao fechar a janela.
                stage.setOnCloseRequest(e -> {
                    Platform.exit();
                    System.exit(0);
                });
            });
            ((Stage) borderPane.getScene().getWindow()).close();
        } else {
            Util.showError("Usuário ou senha inválidos!");
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
        return true;
    }

    @FXML
    void abrirCadastro(ActionEvent event) {
        Util.openView("Cadastro", "Cadastro");
    }

    @FXML
    void initialize() {
        // Verificar se o banco de dados está funcionando.
        OthersDAO.test();

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            model.email = newValue;
        });
        txtSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            model.senha = newValue;
        });
    }

}
