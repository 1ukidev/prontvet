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
            if (table.getSelectionModel().getSelectedItem() != null) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(table, e.getScreenX(), e.getScreenY());
                } else {
                    contextMenu.hide();
                }
            }
        });

        model.tutores = TutorDAO.getInstance().findAll();

        Log.debug("Tutores encontrados: " + model.tutores.size());
        if (!model.tutores.isEmpty()) {
            table.getItems().addAll(model.tutores);
        }
    }

    private void criarMenuContexto() {
        MenuItem item = new MenuItem("Editar");
        item.setOnAction(e -> {
            TutorEntity tutorEntity = table.getSelectionModel().getSelectedItem();
            Util.openView("EditarTutor", "Editando tutor " + tutorEntity.getNome(),  (stage, loader) -> {
                EditarTutorController controller = (EditarTutorController) loader.getController();
                controller.initialize(tutorEntity);

                stage.setOnHidden(event -> {
                    table.getItems().clear();
                    model.tutores = TutorDAO.getInstance().findAll();
                    table.getItems().addAll(model.tutores);
                });
            });
        });

        MenuItem item2 = new MenuItem("Excluir");
        item2.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Deseja realmente excluir o tutor?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    TutorEntity tutorEntity = table.getSelectionModel().getSelectedItem();
                    if (TutorDAO.getInstance().delete(tutorEntity)) {
                        table.getItems().remove(tutorEntity);
                        Util.showSuccess("Tutor excluído com sucesso!");
                        Log.debug("Tutor excluído com sucesso!");
                    } else {
                        Util.showError("Erro ao excluir tutor!");
                        Log.error("Erro ao excluir tutor!");
                    }
                }
            });
        });

        contextMenu.getItems().addAll(item, item2);
    }

}
