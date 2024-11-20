package appli.todolistjx.entity;

public class UtilisateurConnecte extends User {
    private static UtilisateurConnecte INSTANCE;

    private UtilisateurConnecte(int id, String nom, String prenom, String email, String mdp) {
        super(id, nom, prenom, email, mdp);
    }

    public static boolean initInstance(User user) {
        if (INSTANCE == null && user != null) {
            INSTANCE = new UtilisateurConnecte(user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), user.getMdp());
            return true;
        }
        return false;
    }

    public static UtilisateurConnecte getInstance() {
        return INSTANCE;
    }

    public static boolean clearInstance() {
        if (INSTANCE != null) {
            INSTANCE = null;
            return true;
        }
        return false;
    }
}
