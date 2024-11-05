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
import prontvet.dao.TutorDAO;
import prontvet.model.ListaTutoresModel;
import prontvet.table.TutorEntity;

public class ListaTutoresController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<TutorEntity> table;

    @FXML
    private TableColumn<TutorEntity, String> endereco;

    @FXML
    private TableColumn<TutorEntity, Integer> id;

    @FXML
    private TableColumn<TutorEntity, String> nome;

    @FXML
    private TableColumn<TutorEntity, String> telefone;

    private ContextMenu contextMenu = new ContextMenu();

    private ListaTutoresModel model = new ListaTutoresModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<TutorEntity, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("endereco"));
        telefone.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("telefone"));

        criarMenuContexto();
        table.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(table, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

        Log.debug("Buscando tutores no banco de dados...");
        model.tutores = TutorDAO.getInstance().findAll();

        if (!model.tutores.isEmpty()) {
            Log.debug("Tutores encontrados: " + model.tutores.size());
            table.getItems().addAll(model.tutores);
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
