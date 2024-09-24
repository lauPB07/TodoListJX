package appli.todolistjx.repository;

import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.User;
import javafx.scene.control.Label;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public void inscription(String nom, String prenom, String email, String mdp){

        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql1 = "INSERT INTO utilisateur (nom,prenom,email,mdp) VALUES (?,?,?,?) ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setString(1,nom);
            requete.setString(2,prenom);
            requete.setString(3,email);
            requete.setString(4,bcrypt.encode(mdp));
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public  User connexion(String identifiant, String mdp, Label label){
        User user = null;
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur WHERE email = ?  AND mdp = ? ";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,identifiant);
            requetePrepare.setString(2, mdp);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            if(resultatRequette.next()){
                int id = resultatRequette.getInt(1);
                String nom = resultatRequette.getString(2);
                String prenom = resultatRequette.getString(3);
                String email = resultatRequette.getString(4);
                String mdP = resultatRequette.getString(5);
                user = new User(id,nom, prenom,email,mdP);
                return user;
            }else {
                label.setText("Erreur veuillez re essayer");

            }
        }catch (Exception e ){
            System.out.println(e.getMessage());

        }
        return user;
    }
}
