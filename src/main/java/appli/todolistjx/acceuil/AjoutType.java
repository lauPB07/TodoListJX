package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.repository.TypeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutType {
    @FXML
    private TextField codeCouleur;

    @FXML
    private Label erreur;

    @FXML
    private TextField nomType;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    TypeRepository typeRepository = new TypeRepository();

    @FXML
    void retour(ActionEvent event){
        StartApplication.changeScene("acceuil/typeView", "Types de taches");
    }

    @FXML
    void ajoutType(ActionEvent event){
        typeRepository.ajoutType(nomType.getText(),codeCouleur.getText(),erreur);
    }
}
