package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Tache;
import appli.todolistjx.entity.Type;
import appli.todolistjx.repository.TacheRepository;
import appli.todolistjx.repository.TypeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerTacheController implements Initializable {
    @FXML
    private Label erreur;

    @FXML
    private TextField etatTache;

    @FXML
    private TextField nomTache;

    @FXML
    private Button retour;

    @FXML
    private ComboBox<Type> typeTache;

    @FXML
    private Button valider;

    private Tache tache;
    private String nomType;
    TacheRepository tacheRepository = new TacheRepository();
    TypeRepository typeRepository = new TypeRepository();
    private Liste liste;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomTache.setText(tache.getNom());
        etatTache.setText(String.valueOf(tache.getEtat()));
        typeRepository.recupType(typeTache);
        //typeTache.setValue(tache.getRef_type());

        valider.setOnAction(event -> {
            modifier(event);
        });
        retour.setOnAction(event -> {
            retour(event);
        });


    }

    public EditerTacheController(Tache tachesel, Liste liste){
        this.tache = tachesel;
        this.liste = liste;
    }

    @FXML
    void modifier(ActionEvent event){
        tacheRepository.updateTache(nomTache.getText(),Integer.valueOf(etatTache.getText()),typeTache.getValue().getId(),tache.getId(),erreur);
    }

    @FXML
    void retour(ActionEvent event){
        StartApplication.changeScene("acceuil/tacheView", new AcceuilTaches(liste));
    }

}
