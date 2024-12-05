package appli.todolistjx.repository;

import appli.todolistjx.StartApplication;
import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.User;
import appli.todolistjx.entity.UtilisateurConnecte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            StartApplication.changeScene("acceuil/acceuil", "accueil");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "INSERT INTO utilisateur_liste (ref_utilisateur,ref_liste) VALUES (?,?) ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            requete.setInt(1, UtilisateurConnecte.getInstance().getId());
            requete.setInt(2,dernierId());
            requete.executeUpdate();
        } catch (SQLException e) {
              throw new RuntimeException(e);
        }
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

    public ArrayList<Liste> recupererListe() {
        ArrayList<Liste> liste = new ArrayList<>();
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM liste AS l INNER JOIN utilisateur_liste AS u ON u.ref_liste = l.id_liste WHERE ref_utilisateur = ?  ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, UtilisateurConnecte.getInstance().getId());
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Liste(resultatRequette.getInt("id_liste"),resultatRequette.getString("nom")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public void deleteListe(int id, Label label){
        String sql3 = "DELETE FROM tache WHERE ref_liste = ?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql3);
            requetePrepare.setInt(1, id);
            requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DELETE FROM liste where id_liste =?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            requetePrepare.executeUpdate();
            label.setText("Liste bien supprimer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql2 = "DELETE FROM utilisateur_liste WHERE  ref_liste = ?  ";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql2);
            requetePrepare.setInt(1, id);
            requetePrepare.executeUpdate();
            label.setText("Liste bien supprimer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateListe(String nom, int id, Label label){
        String sql = "UPDATE liste SET nom = ? WHERE id_liste = ?";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,nom);
            requete.setInt(2,id);
            requete.executeUpdate();
            label.setText("La liste a bien été modifier");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }
}
