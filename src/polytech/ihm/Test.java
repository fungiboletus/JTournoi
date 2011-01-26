package polytech.ihm;
import java.util.ArrayList;
import java.util.Scanner;

import polytech.personnes.*;
import polytech.stock.*;
import polytech.tools.*;
import polytech.jtournoi.*;


public class Test {

	/**
	 * Test function
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        Main main = new Main();
        // On log la personne qui veut se logger
        while (main.loggedName.equals("")) {
            System.out.println("Entrez votre nom :");
            Scanner s = new Scanner(System.in);
            String lue = s.nextLine();
            main.log(lue);
        }
        System.out.println("Merci "+main.loggedName+", vous êtes correctement loggé.");
    }

}


class Main {

    // Le nom de la personne loggée
    protected String loggedName;
    //Le type de la personne loggée
    protected String loggedType;

    public Main(){
        loggedName = "";
        loggedType = "";
    }

    // L'écran de l'organisateur
    public void organisateur() {
        /*
         * // On charge equipes.xml dans l'arraylist equipes locale equipes =
         * xml.getEquipes(); sports = xml.getSports(); arbitres =
         * xml.getArbitres(); tournois = xml.getTournois();
         */
        while (true) {
            System.out.println("****************************************");
            System.out.println("*              TOURNOI                 *");
            System.out.println("****************************************");
            System.out.println("*  1. Créer une équipe                 *");
            System.out.println("*  2. Gérer une équipe                 *");
            System.out.println("*  3. Créer un tournoi                 *");
            System.out.println("*  4. Gérer un tournoi                 *");
            System.out.println("*  5. Créer une épreuve                *");
            System.out.println("*  6. Supprimer une épreuve            *");
            System.out.println("*  7. Créer un arbitre                 *");
            System.out.println("*  8. Supprimer un arbitre             *");
            System.out.println("*                                      *");
            System.out.println("*  9. Sauvegarder                      *");
            System.out.println("*  0. Quitter en sauvegardant          *");
            System.out.println("****************************************");
            System.out.print("Votre choix: ");
            try {
                Scanner s = new Scanner(System.in);
                int lue = s.nextInt();
                switch (lue) {
                case 1:
                    System.out.println("lol");
                    break;
                case 2:
                    System.out.println("lol");
                    break;
                case 3:
                    System.out.println("lol");
                    break;
                case 4:
                    System.out.println("lol");
                    break;
                case 5:
                    System.out.println("lol");
                    break;
                case 6:
                    System.out.println("lol");
                    break;
                case 7:
                    System.out.println("lol");
                    break;
                case 8:
                    System.out.println("lol");
                    break;
                case 9:
                    System.out.println("lol");
                    break;
                case 0:
                    System.out.println("lol");
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Permet de logger une personne, quelle qu'elle soit
     * 
     * @param nom
     *           Nom de log
     * @return true of false
     *           Le succès ou l'échec du log
     */
    public boolean log(String nom){
        Personne personne;
        // On vérifie si c'est un organisateur
        for (int i = 0; i < Stock.getOrganisateurs().size(); i++) {
            if (nom.equals(Stock.getOrganisateurs().get(i).getNom())) {
                // On demande le mot de passe
                System.out.println("Entrez votre mot de passe :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                // Si tout est bon, l'utilisateur est loggé
                if (lue.equals(Stock.getOrganisateurs().get(i).getPassword())) {
                    loggedName = Stock.getOrganisateurs().get(i).getNom();
                    loggedType = "organisateur";
                }
                break;
            }
        }
        // On vérifie si c'est un arbitre
        if(loggedName.equals("")){
            for (int i = 0; i < Stock.getArbitres().size(); i++) {
                if (nom.equals(Stock.getArbitres().get(i).getNom())) {
                    // On demande le mot de passe
                    System.out.println("Entrez votre mot de passe :");
                    Scanner s = new Scanner(System.in);
                    String lue = s.nextLine();
                    // Si tout est bon, l'utilisateur est loggé
                    if (lue.equals(Stock.getArbitres().get(i).getPassword())) {
                        loggedName = Stock.getArbitres().get(i).getNom();
                        loggedType = "arbitre";
                    }
                    break;
                }
            }
        }
        // On vérifie si c'est un joueur
        if(loggedName.equals("")){
            for (int i = 0; i < Stock.getJoueurs().size(); i++) {
                if (nom.equals(Stock.getJoueurs().get(i).getNom())) {
                    // On demande le mot de passe
                    System.out.println("Entrez votre mot de passe :");
                    Scanner s = new Scanner(System.in);
                    String lue = s.nextLine();
                    // Si tout est bon, l'utilisateur est loggé
                    if (lue.equals(Stock.getJoueurs().get(i).getPassword())) {
                        loggedName = Stock.getJoueurs().get(i).getNom();
                        loggedType = "joueur";
                    }
                    break;
                }
            }
        }
        //Si le log n'a pas marché
        if(loggedName.equals("")) return false;
        // Sinon ca a marché
        return true;
    }
    /*
    private void loggpart(ArrayList<Personne> p, String type){
        for (int i = 0; i < p.size(); i++) {
            if (nom.equals(p.get(i).getNom())) {
                // On demande le mot de passe
                System.out.println("Entrez votre mot de passe :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                // Si tout est bon, l'utilisateur est loggé
                if (lue.equals(p.get(i).getPassword())) {
                    loggedName = p.get(i).getNom();
                    loggedType = type;
                }
                break;
            }
        }
    }
    */
}
