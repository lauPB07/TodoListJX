package appli.todolistjx.entity;

public class Liste {
    public Liste(String id, String nom) {
        this.idListe = id;
        this.nom = nom;
    }

    private String idListe;

    public String getIdListe() {
        return idListe;
    }

    public void setIdListe(String id) {
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
