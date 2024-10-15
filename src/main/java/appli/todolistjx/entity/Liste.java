package appli.todolistjx.entity;

public class Liste {
    public Liste(int id, String nom) {
        this.idListe = id;
        this.nom = nom;
    }

    private int idListe;

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int id) {
        this.idListe = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;
}
