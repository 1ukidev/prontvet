package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.dao.PacienteDAO;
import prontvet.model.ListaPacientesModel;
import prontvet.table.PacienteEntity;

public class ListaPacientesController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<PacienteEntity> table;

    @FXML
    private TableColumn<PacienteEntity, Integer> id;

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

    private ContextMenu contextMenu = new ContextMenu();

    private ListaPacientesModel model = new ListaPacientesModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Integer>("id"));
        idade.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Integer>("idade"));
        nome.setCellValueFactory(new PropertyValueFactory<PacienteEntity, String>("nome"));
        peso.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Double>("peso"));
        raca.setCellValueFactory(new PropertyValueFactory<PacienteEntity, String>("raca"));
        sexo.setCellValueFactory(new PropertyValueFactory<PacienteEntity, Character>("sexo"));

        criarMenuContexto();
        table.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(table, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

        Log.debug("Buscando pacientes no banco de dados...");
        model.pacientes = PacienteDAO.getInstance().findAll();

        if (!model.pacientes.isEmpty()) {
            Log.debug("Pacientes encontrados: " + model.pacientes.size());
            table.getItems().addAll(model.pacientes);
        }
    }

    private void criarMenuContexto() {
        MenuItem item = new MenuItem("Editar");
        item.setOnAction(e -> {
            Log.debug("Clicado");
        });
        MenuItem item2 = new MenuItem("Excluir");
        item2.setOnAction(e -> {
            Log.debug("Clicado");
        });
        contextMenu.getItems().addAll(item, item2);
    }

}
