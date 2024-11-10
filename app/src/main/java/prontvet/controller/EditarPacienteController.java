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

    private EditarPacienteModel model = new EditarPacienteModel();

    @FXML
    void editar(ActionEvent event) {
        if (validateModel()) {
            if (PacienteDAO.getInstance().update(model.pacienteEntity)) {
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
        if (model.pacienteEntity.getTutor() == null) {
            Util.showError("O tutor precisa ser selecionado!");
            return false;
        }
        if (model.pacienteEntity.getNome() == null || model.pacienteEntity.getNome().isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (model.pacienteEntity.getRaca() == null || model.pacienteEntity.getRaca().isEmpty()) {
            Util.showError("A raça precisa ser preenchida!");
            return false;
        }
        if (model.pacienteEntity.getSexo() == null) {
            Util.showError("O sexo precisa ser selecionado!");
            return false;
        }
        if (model.pacienteEntity.getIdade() <= 0) {
            Util.showError("A idade precisa ser preenchida!");
            return false;
        }
        if (model.pacienteEntity.getPeso() <= 0d) {
            Util.showError("O peso precisa ser preenchido!");
            return false;
        }
        if (model.pacienteEntity.getDescricao() == null || model.pacienteEntity.getDescricao().isEmpty()) {
            Util.showError("A descrição precisa ser preenchida!");
            return false;
        }
        return true;
    }

    @FXML
    void initialize(PacienteEntity pacienteEntity) {
        if (pacienteEntity != null) {
            model.pacienteEntity = pacienteEntity;
            boxTutor.getSelectionModel().select(pacienteEntity.getTutor());
            txtNome.setText(pacienteEntity.getNome());
            txtRaca.setText(pacienteEntity.getRaca());
            boxSexo.getSelectionModel().select(pacienteEntity.getSexo() == 'M' ? "Macho" : "Fêmea");
            txtIdade.setText(String.valueOf(pacienteEntity.getIdade()));
            txtPeso.setText(String.valueOf(pacienteEntity.getPeso()));
            txtDescricao.setText(pacienteEntity.getDescricao());
        }

        boxSexo.getItems().add("Macho");
        boxSexo.getItems().add("Fêmea");

        List<TutorEntity> tutorEntities = TutorDAO.getInstance().findAll();
        if (tutorEntities != null && !tutorEntities.isEmpty()) {
            boxTutor.getItems().addAll(tutorEntities);
        }

        boxTutor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.pacienteEntity.setTutor(newValue);
        });
        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            model.pacienteEntity.setNome(newValue);
        });
        txtRaca.textProperty().addListener((observable, oldValue, newValue) -> {
            model.pacienteEntity.setRaca(newValue);
        });
        boxSexo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.pacienteEntity.setSexo(newValue.equals("Macho") ? 'M' : 'F');
        });

        txtIdade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*") && newValue.length() <= 3) {
                model.pacienteEntity.setIdade(newValue.isEmpty() ? 0 : Integer.parseInt(newValue));
            } else {
                txtIdade.setText(oldValue);
            }
        });
        txtPeso.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*(\\.\\d*)?") && newValue.length() <= 6) {
                model.pacienteEntity.setPeso(newValue.isEmpty() || newValue.equals(".") ? 0d : Double.parseDouble(newValue));
            } else {
                txtPeso.setText(oldValue);
            }
        });
        txtDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
            model.pacienteEntity.setDescricao(newValue);
        });
    }

}
