package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Type;
import appli.todolistjx.repository.TacheRepository;
import appli.todolistjx.repository.TypeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutTache implements Initializable {

    @FXML
    private TextField etatTache;

    @FXML
    private Label labelErreur;

    @FXML
    private TextField nomTache;

    @FXML
    private Button retour;

    @FXML
    private ComboBox<Type> typeTache;

    @FXML
    private Button valider;

    private Liste liste;
    TacheRepository tacheRepository = new TacheRepository();

    TypeRepository typeRepository = new TypeRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeRepository.recupType(typeTache);
        retour.setOnAction(event -> {
            retour(event);
        });
        valider.setOnAction(event -> {
            ajoutTache(event);
        });
    }

    public AjoutTache(Liste listesel) {
        this.liste = listesel;
    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/tacheView",new AcceuilTaches(liste));
    }

    @FXML
    void ajoutTache(ActionEvent event){
        tacheRepository.createTache(nomTache.getText(),Integer.parseInt(etatTache.getText()),liste.getIdListe(),typeTache.getValue().getId(),labelErreur);
    }


}
