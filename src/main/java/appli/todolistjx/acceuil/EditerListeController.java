package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.repository.ListeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerListeController implements Initializable {
    @FXML
    private Label erreurLabel;

    @FXML
    private TextField nomField;

    @FXML
    private Button edit;

    @FXML
    private Button retour;


    private Liste liste;
    private ListeRepository listeRepository = new ListeRepository();

    public EditerListeController(Liste listesel) {
        this.liste = listesel;
    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/acceuil","Acceuil");
    }

    @FXML
    void modifier(ActionEvent event){
        listeRepository.updateListe(nomField.getText(),liste.getIdListe(),erreurLabel);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nomField.setText(liste.getNom());
        edit.setOnAction(event -> {
            modifier(event);
        });
        retour.setOnAction(event ->{
            retour(event);
        });

    }
}
