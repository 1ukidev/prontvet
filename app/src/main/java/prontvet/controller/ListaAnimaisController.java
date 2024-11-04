package prontvet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import prontvet.Log;
import prontvet.dao.AnimalDAO;
import prontvet.model.ListaAnimaisModel;
import prontvet.table.AnimalEntity;

public class ListaAnimaisController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView<AnimalEntity> table;

    @FXML
    private TableColumn<AnimalEntity, Integer> id;

    @FXML
    private TableColumn<AnimalEntity, Integer> idade;

    @FXML
    private TableColumn<AnimalEntity, String> nome;

    @FXML
    private TableColumn<AnimalEntity, Double> peso;

    @FXML
    private TableColumn<AnimalEntity, String> raca;

    @FXML
    private TableColumn<AnimalEntity, Character> sexo;

    private ListaAnimaisModel model = new ListaAnimaisModel();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<AnimalEntity, Integer>("id"));
        idade.setCellValueFactory(new PropertyValueFactory<AnimalEntity, Integer>("idade"));
        nome.setCellValueFactory(new PropertyValueFactory<AnimalEntity, String>("nome"));
        peso.setCellValueFactory(new PropertyValueFactory<AnimalEntity, Double>("peso"));
        raca.setCellValueFactory(new PropertyValueFactory<AnimalEntity, String>("raca"));
        sexo.setCellValueFactory(new PropertyValueFactory<AnimalEntity, Character>("sexo"));

        Log.debug("Buscando animais no banco de dados...");
        model.animais = AnimalDAO.getInstance().findAll();

        if (!model.animais.isEmpty()) {
            Log.debug("Animais encontrados: " + model.animais.size());
            table.getItems().addAll(model.animais);
        }
    }

}
