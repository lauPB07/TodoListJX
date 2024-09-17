package appli.todolistjx.bdd;

import appli.todolistjx.entity.User;
import javafx.scene.control.Label;

import java.sql.*;

public class Bdd {

    public Connection bdd;

    public Connection getBdd() {
        String bddNom = "crud";
        String user ="root";
        String usermdp ="";
        String url ="jdbc:mysql://localhost:3306/" + bddNom;

        try {
            bdd = DriverManager.getConnection(url,user,usermdp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bdd;
    }


}
