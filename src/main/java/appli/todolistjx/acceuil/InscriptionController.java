package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscriptionController {

    @FXML
    private PasswordField comfirmationField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private Label messageErreurField;

    @FXML
    void inscription(ActionEvent event) {

        if(!mdpField.getText().equals(comfirmationField.getText())){
           messageErreurField.setText("Erreur");

        } else {
            UserRepository inscription = new UserRepository();
            inscription.inscription(nomField.getText(),prenomField.getText(),emailField.getText(),mdpField.getText());
            messageErreurField.setText("Nouvelle utilisateur enregistrer !");

        }
    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/loginView","Connexion");
    }



}
