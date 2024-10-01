package appli.todolistjx.acceuil;

import appli.todolistjx.entity.Liste;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private TableView<Liste> tableauListe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID Liste","idListe"},
                {"Nom", "non"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Liste,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][0]));
            tableauListe.getColumns().add(maColonne);
        }

    }

}
