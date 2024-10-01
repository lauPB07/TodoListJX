package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class OublieMdp {
    @FXML
    private PasswordField confirmationField;

    @FXML
    private TextField emailField;

    @FXML
    private Label erreur;

    @FXML
    private PasswordField mdpField;

    UserRepository userRepository = new UserRepository();

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/loginView","Connexion");
    }

    @FXML
    void valider(ActionEvent event){
        if(!confirmationField.getText().equals(mdpField.getText())){
            erreur.setText("Le mot mot de passe n'est pas comfirmer");
        }else {
            if(userRepository.getUserByEmail(emailField.getText())==null){
                erreur.setText("Erreur dans l'email");
            }else {
                userRepository.modifMdp(emailField.getText(),mdpField.getText(),erreur);
            }
        }
    }
}
