package appli.todolistjx.repository;

import appli.todolistjx.StartApplication;
import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.User;
import javafx.scene.control.Label;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    static BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public User getUserByEmail(String email){
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur WHERE email = ? ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,email);
            ResultSet resultatRequette = requete.executeQuery();
            if(resultatRequette.next()){
                int id = resultatRequette.getInt(1);
                String nom = resultatRequette.getString(2);
                String prenom = resultatRequette.getString(3);
                String email1 = resultatRequette.getString(4);
                String mdP = resultatRequette.getString(5);
                System.out.println("yes !");
                return new User(id,nom, prenom,email1,mdP);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void inscription(String nom, String prenom, String email, String mdp, Label label){
        if(this.getUserByEmail(email)!=null){
            label.setText("Erreur vous avez deja un compte");
        }else {
            String sql1 = "INSERT INTO utilisateur (nom,prenom,email,mot_de_passe) VALUES (?,?,?,?) ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql1);
                requete.setString(1,nom);
                requete.setString(2,prenom);
                requete.setString(3,email);
                requete.setString(4,bcrypt.encode(mdp));
                requete.executeUpdate();
                label.setText("Nouvelle utilisateur enregistrer !");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }


    public User connexion(String identifiant, String mdp, Label label){
        System.out.println("Id : " + identifiant);
        User user = this.getUserByEmail(identifiant);
        System.out.println("Hello : " + user);

        if (user == null){
            label.setText("erreur vous n'avez pas de compte");
        }else {
            System.out.println("Hello : " + user.getMdp());

            System.out.println(user.getMdp());
            System.out.println(bcrypt.matches(mdp,user.getMdp()));
            if (bcrypt.matches(mdp,user.getMdp())){
                StartApplication.changeScene("acceuil/acceuil","Connexion");
                return user;
            }else {
                label.setText("Mot de passe incorrect");
            }
        }
       return null;
    }

    public void modifMdp(String email, String mdp, Label label){
        String sql = "UPDATE utilisateur SET mot_de_passe = ? WHERE email = ?";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,email);
            requete.setString(2,bcrypt.encode(mdp));
            requete.executeUpdate();
            label.setText("Le mot de passe a bien été modifier");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

    }


}
