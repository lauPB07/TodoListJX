package appli.todolistjx.acceuil;

import appli.todolistjx.StartApplication;
import appli.todolistjx.bdd.Bdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscriptionController {

    @FXML
    private TextField comfirmationField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private Label messageErreurField;

    @FXML
    void inscription(ActionEvent event) {
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();

        if(!mdpField.getText().equals(comfirmationField.getText())){
           messageErreurField.setText("Erreur");

        }else {
            String sql1 = "INSERT INTO utilisateur (nom,prenom,email,mdp) VALUES (?,?,?,?) ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql1);
                requete.setString(1,nomField.getText());
                requete.setString(2,prenomField.getText());
                requete.setString(3,emailField.getText());
                requete.setString(4,mdpField.getText());
                requete.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


    }

    @FXML
    void retour(ActionEvent event) {
        StartApplication.changeScene("acceuil/loginView","Connexion");
    }



}
