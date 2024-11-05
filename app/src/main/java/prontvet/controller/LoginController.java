package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import prontvet.Util;
import prontvet.dao.OthersDAO;
import prontvet.dao.UsuarioDAO;
import prontvet.table.UsuarioEntity;

public class LoginController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink linkSeCadastrar;

    @FXML
    void login(ActionEvent event) {
        UsuarioEntity usuario = new UsuarioEntity(txtNome.getText(), txtSenha.getText());
        if (UsuarioDAO.getInstance().find(usuario)) {
            Util.openView("Main", "ProntVet");
            borderPane.getScene().getWindow().hide();
        } else {
            Util.showError("Usuário ou senha inválidos!");
        }
    }

    @FXML
    void abrirCadastro(ActionEvent event) {
        Util.openView("Cadastro", "Cadastro");
    }

    @FXML
    void initialize() {
        // Verificar se o banco de dados está funcionando.
        OthersDAO.test();
    }

}
