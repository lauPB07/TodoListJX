package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.Type;
import appli.todolistjx.repository.TypeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerTypeController implements Initializable {

    @FXML
    private TextField codeCouleur;

    @FXML
    private Label erreur;

    @FXML
    private TextField nomtype;

    @FXML
    private Button retour;

    @FXML
    private Label titre;

    @FXML
    private Button valider;

    private Type type;
    TypeRepository typeRepository =new TypeRepository();

    public EditerTypeController(Type typesel){this.type = typesel;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nomtype.setText(type.getNom());
        this.codeCouleur.setText(type.getCodeCouleur());

        valider.setOnAction(event -> {
            modifier(event);
        });
        retour.setOnAction(event -> {
            retour(event);
        });
    }

    @FXML
    void modifier(ActionEvent event){
        typeRepository.updateType(nomtype.getText(),codeCouleur.getText(),type.getId(),erreur);
    }

    @FXML
    void retour(ActionEvent event){
        StartApplication.changeScene("acceuil/typeView","Types de taches");
    }
}
