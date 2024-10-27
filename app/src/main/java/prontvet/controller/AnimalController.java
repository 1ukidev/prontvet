package prontvet.controller;

import java.util.regex.PatternSyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.Util;
import prontvet.model.AnimalModel;

public class AnimalController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private MenuButton menuSexo;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtRaca;

    @FXML
    private ChoiceBox<String> boxSexo;

    private AnimalModel model = new AnimalModel();

    @FXML
    void cadastrar(ActionEvent event) {
        if (validateModel()) {
            Log.debug("Cadastrando...");
            Log.debug("Nome: " + model.nome);
            Log.debug("Raça: " + model.raca);
            Log.debug("Sexo: " + model.sexo);
            Log.debug("Idade: " + model.idade);
            Log.debug("Peso: " + model.peso);
            Log.debug("Cadastrado com sucesso!");
            Util.showSuccess("Animal cadastrado com sucesso!");
        }
    }

    private boolean validateModel() {
        if (model.nome == null || model.nome.isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (model.raca == null || model.raca.isEmpty()) {
            Util.showError("A raça precisa ser preenchida!");
            return false;
        }
        if (model.sexo == null || model.sexo.equals(' ')) {
            Util.showError("O sexo precisa ser selecionado!");
            return false;
        }
        if (model.idade == null || model.idade <= 0) {
            Util.showError("A idade precisa ser preenchida!");
            return false;
        }
        if (model.peso == null || model.peso <= 0) {
            Util.showError("O peso precisa ser preenchido!");
            return false;
        }
        return true;
    }

    @FXML
    public void initialize() {
        boxSexo.getItems().add("Macho");
        boxSexo.getItems().add("Fêmea");

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            model.nome = newValue;
        });
        txtRaca.textProperty().addListener((observable, oldValue, newValue) -> {
            model.raca = newValue;
        });
        boxSexo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.sexo = (newValue.equals("Macho")) ? 'M' : 'F';
        });

        txtIdade.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.matches("\\d*")) {
                    model.idade = Integer.parseInt(newValue);
                } else {
                    txtIdade.setText(oldValue);
                }
            } catch (PatternSyntaxException e) {}
        });
        txtPeso.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.matches("\\d*")) {
                    model.peso = Double.parseDouble(newValue);
                } else {
                    txtPeso.setText(oldValue);
                }
            } catch (PatternSyntaxException e) {}
        });
    }

}
