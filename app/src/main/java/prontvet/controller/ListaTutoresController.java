package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.dao.TutorDAO;
import prontvet.model.ListaTutoresModel;
import prontvet.table.TutorTable;

public class ListaTutoresController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<TutorTable> table;

    @FXML
    private TableColumn<TutorTable, String> endereco;

    @FXML
    private TableColumn<TutorTable, Integer> id;

    @FXML
    private TableColumn<TutorTable, String> nome;

    @FXML
    private TableColumn<TutorTable, String> telefone;

    private ListaTutoresModel model = new ListaTutoresModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<TutorTable, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<TutorTable, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<TutorTable, String>("endereco"));
        telefone.setCellValueFactory(new PropertyValueFactory<TutorTable, String>("telefone"));

        Log.debug("Buscando tutores no banco de dados...");
        model.tutores = TutorDAO.getInstance().findAll();

        if (!model.tutores.isEmpty()) {
            Log.debug("Tutores encontrados: " + model.tutores.size());
            table.getItems().addAll(model.tutores);
        }
    }

}
