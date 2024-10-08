package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.repository.ListeRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private TableView<Liste> tableauListe;

    ListeRepository listeRepo = new ListeRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID Liste","idListe"},
                {"Nom", "nom"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Liste,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauListe.getColumns().add(maColonne);
        }
        ArrayList<Liste> list = listeRepo.recupererListe();
        tableauListe.getItems().addAll(list);





    }
    @FXML
    void ajoutListe(ActionEvent actionEvent){
        StartApplication.changeScene("acceuil/ajoutListe","Ajouter une liste");
    }

    @FXML
    void desable(ActionEvent actionEvent){
        TablePosition cell = tableauListe.getSelectionModel().getSelectedCells().get(0);
        int indexLigne = cell.getRow();
        TableColumn colone = cell.getTableColumn();
        int id = Integer.parseInt(colone.getText());
        listeRepo.deleteListe(id);
    }

    @FXML
    void onListeSelection(MouseEvent event){
        /*if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){

        }*/
    }



}
