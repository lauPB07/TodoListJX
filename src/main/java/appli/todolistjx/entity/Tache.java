package appli.todolistjx.entity;

public class Tache {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getRef_liste() {
        return ref_liste;
    }

    public void setRef_liste(int ref_liste) {
        this.ref_liste = ref_liste;
    }

    public int getRef_type() {
        return ref_type;
    }

    public void setRef_type(int ref_type) {
        this.ref_type = ref_type;
    }

    public Tache(int id, String nom, int etat, int ref_liste, int ref_type) {
        this.id = id;
        this.nom = nom;
        this.etat = etat;
        this.ref_liste = ref_liste;
        this.ref_type = ref_type;
    }

    private int id;
    private String nom;
    private int etat;
    private int ref_liste;
    private int ref_type;
}
