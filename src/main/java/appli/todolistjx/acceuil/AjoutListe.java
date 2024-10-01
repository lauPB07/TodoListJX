package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.repository.ListeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutListe {
    @FXML
    private Label erreurLabel;

    @FXML
    private TextField nomField;

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/acceuil","Acceuil");
    }

    @FXML
    void createListe(ActionEvent event){
        ListeRepository listeRepository = new ListeRepository();
        listeRepository.ajoutListe(nomField.getText(),erreurLabel);
    }

}
