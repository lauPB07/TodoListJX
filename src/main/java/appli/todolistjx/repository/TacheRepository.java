package appli.todolistjx.repository;

import appli.todolistjx.bdd.Bdd;
import appli.todolistjx.entity.Liste;
import appli.todolistjx.entity.Tache;
import appli.todolistjx.entity.UtilisateurConnecte;

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
}
