package prontvet.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.PacienteDAO;
import prontvet.dao.TutorDAO;
import prontvet.model.EditarPacienteModel;
import prontvet.table.PacienteEntity;
import prontvet.table.TutorEntity;

public class EditarPacienteController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnEditar;

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
    private TextField txtDescricao;

    @FXML
    private ChoiceBox<String> boxSexo;

    @FXML
    private ChoiceBox<TutorEntity> boxTutor;

    public EditarPacienteModel model = new EditarPacienteModel();

    @FXML
    void editar(ActionEvent event) {
        if (validateModel()) {
            PacienteEntity pacienteEntity = new PacienteEntity(
                model.tutor, model.nome, model.raca,
                model.sexo, model.idade, model.peso, model.descricao
            );
            pacienteEntity.setId(model.id);

            if (PacienteDAO.getInstance().update(pacienteEntity)) {
                Util.showSuccess("Paciente editado com sucesso!");
                Log.debug("Paciente editado com sucesso!");
                ((Stage) borderPane.getScene().getWindow()).close();
            } else {
                Util.showError("Erro ao editar paciente!");
                Log.error("Erro ao editar paciente!");
            }
        }
    }

    private boolean validateModel() {
        if (model.tutor == null) {
            Util.showError("O tutor precisa ser selecionado!");
            return false;
        }
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
        if (model.descricao == null || model.descricao.isEmpty()) {
            Util.showError("A descrição precisa ser preenchida!");
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        boxSexo.getItems().add("Macho");
        boxSexo.getItems().add("Fêmea");

        List<TutorEntity> tutorEntities = TutorDAO.getInstance().findAll();
        if (tutorEntities != null && !tutorEntities.isEmpty()) {
            boxTutor.getItems().addAll(tutorEntities);
        }

        boxTutor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.tutor = newValue;
        });
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
        txtDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
            model.descricao = newValue;
        });
    }

}
