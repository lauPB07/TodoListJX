package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.entity.User;
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
            User.connexion(emailField.getText(),messageErreurField.getText(),messageErreurField);
            StartApplication.changeScene("acceuil/acceuil","acceuil");
        }
    }

    @FXML
    void inscription(ActionEvent event) {
        StartApplication.changeScene("acceuil/inscription","Inscription");
    }

    @FXML
    void motDePasseOublie(ActionEvent event) {

    }

}