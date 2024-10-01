package appli.todolistjx.repository;

import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.User;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    private int id;

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

        //String sql2 = "INSERT INTO utilisateur_liste (ref_utilisateur,ref_liste) VALUES (?,?) ";
        //try {
        //    PreparedStatement requete = connection.prepareStatement(sql2);
         //   requete.setInt(1,user.getId());
         //   requete.setInt(2,dernierId());
         //   requete.executeUpdate();
       // } catch (SQLException e) {
        //    throw new RuntimeException(e);
       // }
    }

    public Integer dernierId(){
        String sql2 = "SELECT MAX(id_liste)FROM liste ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            ResultSet resultatRequette = requete.executeQuery();
            if(resultatRequette.next()){
                id = resultatRequette.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
