package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.dao.AnimalDAO;
import prontvet.model.ListaAnimaisModel;
import prontvet.table.AnimalTable;

public class ListaAnimaisController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<AnimalTable> table;

    @FXML
    private TableColumn<AnimalTable, Integer> id;

    @FXML
    private TableColumn<AnimalTable, Integer> idade;

    @FXML
    private TableColumn<AnimalTable, String> nome;

    @FXML
    private TableColumn<AnimalTable, Double> peso;

    @FXML
    private TableColumn<AnimalTable, String> raca;

    @FXML
    private TableColumn<AnimalTable, Character> sexo;

    private ListaAnimaisModel model = new ListaAnimaisModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<AnimalTable, Integer>("id"));
        idade.setCellValueFactory(new PropertyValueFactory<AnimalTable, Integer>("idade"));
        nome.setCellValueFactory(new PropertyValueFactory<AnimalTable, String>("nome"));
        peso.setCellValueFactory(new PropertyValueFactory<AnimalTable, Double>("peso"));
        raca.setCellValueFactory(new PropertyValueFactory<AnimalTable, String>("raca"));
        sexo.setCellValueFactory(new PropertyValueFactory<AnimalTable, Character>("sexo"));

        Log.debug("Buscando animais no banco de dados...");
        model.animais = AnimalDAO.getInstance().findAll();

        if (!model.animais.isEmpty()) {
            Log.debug("Animais encontrados: " + model.animais.size());
            table.getItems().addAll(model.animais);
        }
    }

}
