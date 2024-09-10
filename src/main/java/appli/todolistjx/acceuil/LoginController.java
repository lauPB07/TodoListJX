package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
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
    }

    @FXML
    void inscription(ActionEvent event) {
        StartApplication.changeScene("acceuil/inscription","Inscription");
    }

    @FXML
    void motDePasseOublie(ActionEvent event) {

    }

}