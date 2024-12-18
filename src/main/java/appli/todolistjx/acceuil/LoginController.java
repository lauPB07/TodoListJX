package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.User;
import appli.todolistjx.entity.UtilisateurConnecte;
import appli.todolistjx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private Label messageErreurField;

    @FXML
    void connexion(ActionEvent event) {
        System.out.println("L email est : "+emailField.getText());
        System.out.println("Le mot de passe est : "+mdpField.getText());
        if(emailField.getText().isBlank()  &&  mdpField.getText().isBlank()){
            messageErreurField.setText("entrer l'email et le mot de passe ");
        }else {
            UserRepository userRepository = new UserRepository();
             User user = userRepository.connexion(emailField.getText(),mdpField.getText(),messageErreurField);
            if (user != null) {
                boolean isInitialized = UtilisateurConnecte.initInstance(user);

                if (isInitialized) {
                    StartApplication.changeScene("acceuil/acceuil", "accueil");
                } else {
                    messageErreurField.setText("Une session est déjà active.");
                }
            }

        }
    }

    @FXML
    void inscription(ActionEvent event) {
        StartApplication.changeScene("acceuil/inscription","Inscription");
    }

    @FXML
    void motDePasseOublie(ActionEvent event) {
        StartApplication.changeScene("acceuil/oublieMdp","Oublie de mot de passe");
    }

}