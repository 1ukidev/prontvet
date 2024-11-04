package prontvet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.AnimalDAO;
import prontvet.model.CadastrarAnimalModel;
import prontvet.table.AnimalEntity;

public class CadastrarAnimalController {

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

    private CadastrarAnimalModel model = new CadastrarAnimalModel();

    @FXML
    void cadastrar(ActionEvent event) {
        if (validateModel()) {
            AnimalEntity animalTable = new AnimalEntity(
                model.nome, model.raca, model.sexo, model.idade, model.peso
            );
            AnimalDAO.getInstance().save(animalTable);

            if (animalTable.getId() != null) {
                Util.showSuccess("Animal cadastrado com sucesso!");
                Log.debug("Animal cadastrado com sucesso!");
                borderPane.getScene().getWindow().hide();
            } else {
                Util.showError("Erro ao cadastrar animal!");
                Log.error("Erro ao cadastrar animal!");
            }
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
    void initialize() {
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
            if (newValue.matches("\\d*") && newValue.length() <= 3) {
                model.idade = newValue.isEmpty() ? 0 : Integer.parseInt(newValue);
            } else {
                txtIdade.setText(oldValue);
            }
        });
        txtPeso.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*(\\.\\d*)?") && newValue.length() <= 6) {
                model.peso = newValue.isEmpty() || newValue.equals(".") ? 0d : Double.parseDouble(newValue);
            } else {
                txtPeso.setText(oldValue);
            }
        });
    }

}
