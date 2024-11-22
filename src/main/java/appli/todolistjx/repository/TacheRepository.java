package appli.todolistjx.repository;

import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.Tache;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TacheRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    private int id;

    public ArrayList<Tache> recupererListe(int id) {
        ArrayList<Tache> liste = new ArrayList<>();
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM tache  WHERE ref_liste = ?  ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Tache(resultatRequette.getInt("id_tache"),resultatRequette.getString("nom"),resultatRequette.getInt("etat"),resultatRequette.getInt("ref_liste"),resultatRequette.getInt("ref_type")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public String recupererType(int id ,String nom){
        String sql = "SELECT ty.nom FROM type as ty INNER JOIN tache as t ON t.ref_type = ty.id_type WHERE t.id_tache = ? ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
               nom = resultatRequette.getString("nom");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return nom;
    }

    public void createTache(String nom, int etat,int refListe, int refType, Label label){
        String sql1 = "INSERT INTO tache (nom,etat,ref_liste,ref_type) VALUES (?,?,?,?) ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setString(1,nom);
            requete.setInt(2,etat);
            requete.setInt(3,refListe);
            requete.setInt(4,refType);
            requete.executeUpdate();
            label.setText("Nouvelle Tache créer !");
            //StartApplication.changeScene("acceuil/acceuil", "accueil");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }

    public void updateTache(String nom,int etat ,int type,  int id, Label label){
        String sql = "UPDATE tache SET nom = ?, etat = ?, ref_type = ? WHERE id_tache = ?";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,nom);
            requete.setInt(2,etat);
            requete.setInt(3,type);
            requete.setInt(4,id);
            requete.executeUpdate();
            label.setText("La tache a bien été modifier");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }

    public void deleteTache(int id, Label label){
        String sql = "DELETE FROM tache where id_tache =?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            requetePrepare.executeUpdate();
            label.setText("La tache a bien supprimer");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }
    }
}
