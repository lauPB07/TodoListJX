package appli.todolistjx.entity;

public class Type {
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

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public Type(int id, String nom, String codeCouleur) {
        this.id = id;
        this.nom = nom;
        this.codeCouleur = codeCouleur;
    }

    private int id;

    @Override
    public String toString() {
        return "Type : " + nom;
    }

    private String nom;
    private String codeCouleur;
}
