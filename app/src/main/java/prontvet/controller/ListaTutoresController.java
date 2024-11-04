package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ListaTutoresModel model = new ListaTutoresModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<TutorEntity, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("endereco"));
        telefone.setCellValueFactory(new PropertyValueFactory<TutorEntity, String>("telefone"));

        Log.debug("Buscando tutores no banco de dados...");
        model.tutores = TutorDAO.getInstance().findAll();

        if (!model.tutores.isEmpty()) {
            Log.debug("Tutores encontrados: " + model.tutores.size());
            table.getItems().addAll(model.tutores);
        }
    }

}
