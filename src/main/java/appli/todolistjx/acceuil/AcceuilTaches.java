package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Tache;
import appli.todolistjx.repository.TacheRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceuilTaches implements Initializable {


    @FXML
    private Button supprimer;

    @FXML
    private Label labelerreur;

    @FXML
    private TableView<Tache> tableauTaches;

    @FXML
    private Button ajoutTache;


    @FXML
    private Button retour;


    @FXML
    private Label titre;

    TacheRepository tacheRepo = new TacheRepository();
    private Liste liste;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                //{"ID Tache","idTache"},
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

        retour.setOnAction(event ->{
            retour(event);
        });
        tableauTaches.setOnMouseClicked(event -> {
            onListeSelection((MouseEvent) event);
        });
        supprimer.setVisible(false);
        ajoutTache.setOnAction(event -> {
            createTache(event);
        });

        titre.setText("Enssemble des taches de votre liste "+ liste.getNom()+ " : ");



    }

    public AcceuilTaches(Liste listesel) {
        this.liste = listesel;
    }

    @FXML
    void onListeSelection(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){
            TablePosition cell = tableauTaches.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Tache tachesel = tableauTaches.getItems().get(indexLigne);
            System.out.println("Double-clique ligne "+indexLigne+" , colone  "+colone.getText()+ " : "+ tachesel);
            StartApplication.changeScene("acceuil/editerTacheView",new EditerTacheController(tachesel,liste));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = tableauTaches.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Tache tachesel = tableauTaches.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+tachesel);
            supprimer.setVisible(true);
            int id = tachesel.getId();
            supprimer.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la liste ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    tacheRepo.deleteTache(id,labelerreur);
                    supprimer.setVisible(false);
                }else {
                    labelerreur.setText("Supression annulée");
                    supprimer.setVisible(false);
                }
            });

        }

    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/acceuil","Acceuil");
    }

    @FXML
    void createTache(ActionEvent event){
        StartApplication.changeScene("acceuil/ajoutTache",new AjoutTache(liste));
    }
}
