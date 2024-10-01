package appli.todolistjx.repository;

import appli.todolistjx.bdd.Bdd;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListeRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public void ajoutListe(String nom, Label label){
        String sql1 = "INSERT INTO liste (nom) VALUES (?) ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setString(1,nom);
            requete.executeUpdate();
            label.setText("Nouvelle Liste créer !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
