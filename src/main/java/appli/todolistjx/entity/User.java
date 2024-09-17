package appli.todolistjx.entity;

import appli.todolistjx.bdd.Bdd;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String nom;
    private String prenom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public User(){

    }

    public User(int id, String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.id = id;
    }

    private String email;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    private String mdp;

    @Override
    public String toString() {
        return  nom ;
    }

    public static User connexion(String identifiant, String mdp, Label label){
        User user = new User();
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur WHERE email ='"+identifiant+ "' AND mdp ='"+mdp+"'";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
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
