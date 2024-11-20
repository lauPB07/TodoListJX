package appli.todolistjx.acceuil;

import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Tache;
import appli.todolistjx.repository.TacheRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AcceuilTaches implements Initializable {
    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private TableView<Tache> tableauTaches;

    TacheRepository tacheRepo = new TacheRepository();
    private Liste liste;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID Tache","idTache"},
                {"Nom", "nom"},
                {"Etat", "etat"},
                {"Type", "refType"},
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Tache,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauTaches.getColumns().add(maColonne);
        }
        ArrayList<Tache> list = tacheRepo.recupererListe(this.liste.getIdListe());
        tableauTaches.getItems().addAll(list);

    }

    public AcceuilTaches(Liste listesel) {
        this.liste = listesel;
    }

    @FXML
    void onListeSelection(MouseEvent event) {

    }
}
