package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.Util;
import prontvet.dao.PacienteDAO;
import prontvet.model.ListaPacientesModel;
import prontvet.table.PacienteEntity;
import prontvet.table.TutorEntity;

public class ListaPacientesController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<PacienteEntity> table;

    @FXML
    private TableColumn<PacienteEntity, Integer> id;

    @FXML
    private TableColumn<PacienteEntity, TutorEntity> tutor;

    @FXML
    private TableColumn<PacienteEntity, Integer> idade;

    @FXML
    private TableColumn<PacienteEntity, String> nome;

    @FXML
    private TableColumn<PacienteEntity, Double> peso;

    @FXML
    private TableColumn<PacienteEntity, String> raca;

    @FXML
    private TableColumn<PacienteEntity, Character> sexo;

    @FXML
    private TableColumn<PacienteEntity, String> descricao;

    private ContextMenu contextMenu = new ContextMenu();

    private ListaPacientesModel model = new ListaPacientesModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Integer>("id"));
        tutor.setCellValueFactory(new PropertyValueFactory<PacienteEntity, TutorEntity>("tutor"));
        idade.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Integer>("idade"));
        nome.setCellValueFactory(new PropertyValueFactory<PacienteEntity, String>("nome"));
        peso.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Double>("peso"));
        raca.setCellValueFactory(new PropertyValueFactory<PacienteEntity, String>("raca"));
        sexo.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Character>("sexo"));
        descricao.setCellValueFactory(new PropertyValueFactory<PacienteEntity, String>("descricao"));

        criarMenuContexto();
        table.setOnMouseClicked(e -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(table, e.getScreenX(), e.getScreenY());
                } else {
                    contextMenu.hide();
                }
            }
        });

        model.pacientes = PacienteDAO.getInstance().findAll();

        Log.debug("Pacientes encontrados: " + model.pacientes.size());
        if (!model.pacientes.isEmpty()) {
            table.getItems().addAll(model.pacientes);
        }
    }

    private void criarMenuContexto() {
        MenuItem item = new MenuItem("Editar");
        item.setOnAction(e -> {
            PacienteEntity pacienteEntity = table.getSelectionModel().getSelectedItem();
            Util.openView("EditarPaciente", "Editando paciente " + pacienteEntity.getNome(), (stage, loader) -> {
                EditarPacienteController controller = (EditarPacienteController) loader.getController();
                controller.initialize(pacienteEntity);

                stage.setOnHidden(event -> {
                    table.getItems().clear();
                    model.pacientes = PacienteDAO.getInstance().findAll();
                    table.getItems().addAll(model.pacientes);
                });
            });
        });

        MenuItem item2 = new MenuItem("Excluir");
        item2.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Deseja realmente excluir o paciente?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    PacienteEntity pacienteEntity = table.getSelectionModel().getSelectedItem();
                    if (PacienteDAO.getInstance().delete(pacienteEntity)) {
                        table.getItems().remove(pacienteEntity);
                        Util.showSuccess("Paciente excluído com sucesso!");
                        Log.debug("Paciente excluído com sucesso!");
                    } else {
                        Util.showError("Erro ao excluir paciente!");
                        Log.error("Erro ao excluir paciente!");
                    }
                }
            });
        });

        contextMenu.getItems().addAll(item, item2);
    }

}
