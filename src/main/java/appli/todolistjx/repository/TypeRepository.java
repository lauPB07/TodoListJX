package appli.todolistjx.repository;

import appli.todolistjx.StartApplication;
import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Type;
import appli.todolistjx.entity.UtilisateurConnecte;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    private int id;

    public void recupType(ComboBox<Type> comboBox){

        String sql = "SELECT * FROM type ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);;
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_type");
                String nom = resultatRequette.getString("nom");
                String codeCouleur = resultatRequette.getString("code_couleur");
                comboBox.getItems().add(new Type(id, nom,codeCouleur));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public ArrayList<Type> recupererType() {
        ArrayList<Type> liste = new ArrayList<>();
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM type ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Type(resultatRequette.getInt("id_type"),resultatRequette.getString("nom"),resultatRequette.getString("code_couleur")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public void ajoutType(String nom,String codeCouleur, Label label){
        String sql1 = "INSERT INTO type (nom,code_couleur) VALUES (?,?) ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setString(1,nom);
            requete.setString(2,codeCouleur);
            requete.executeUpdate();
            label.setText("Nouveaux Type créer !");
            StartApplication.changeScene("acceuil/acceuil", "accueil");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }

    public void updateType(String nom,String codeCouleur, int id, Label label){
        String sql = "UPDATE type SET nom = ?, code_couleur = ? WHERE id_type = ?";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,nom);
            requete.setString(2,codeCouleur);
            requete.setInt(3,id);
            requete.executeUpdate();
            label.setText("Le type de tache a bien été modifier");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }

    public void deleteType(int id, Label label){
        String sql = "DELETE FROM type where id_type =?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            requetePrepare.executeUpdate();
            label.setText("Le type de tache a bien supprimer");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }
}
