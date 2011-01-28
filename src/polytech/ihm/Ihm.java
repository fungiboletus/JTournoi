package polytech.ihm;

import java.util.List;
import java.util.Scanner;

import polytech.personnes.Joueur;
import polytech.personnes.Personne;
import polytech.stock.Stock;

class Ihm {

    // Le nom de la personne loggée
    protected String loggedName;
    //Le type de la personne loggée
    protected String loggedType;

    public Ihm(){
        loggedName = "";
        loggedType = "";
    }
    
    
    /**
     * Permet de logger une personne, quelle qu'elle soit
     * 
     * @param nom
     *           Nom de log
     */
    public void log(String nom){
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
    }
    
    
    /**
     * Déconnecte quelqu'un de connecté
     * 
     */
    public void unlog(){
        loggedName = "";
        loggedType = "";
    }
    
}