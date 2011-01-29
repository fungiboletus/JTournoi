package polytech.ihm;
import java.util.ArrayList;
import java.util.Scanner;

import polytech.stock.*;
import polytech.jtournoi.*;


public class Test {

    private static Ihm ihm;
    
	/**
	 * Test function
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        ihm = new Ihm();
        //Stock.chargerStock(TypeChargement.SQL);
        GeneratePlayer.main();
        // On log la personne qui veut se logger
        while(true){
            while (ihm.loggedName.equals("")) {
                System.out.println("Entrez votre nom :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                ihm.log(lue);
            }
            System.out.println("Bonjour, "+ihm.loggedName+", vous êtes correctement loggé.");
            
            // On lance l'interface selon l'utilisateur
            if(ihm.loggedType.equals("organisateur")){
                organisateur();
            }
            else if(ihm.loggedType.equals("arbitre")){
                arbitre();
            }
            else if(ihm.loggedType.equals("joueur")){
                joueur();
            }
        }
    }
    
    // L'écran de l'organisateur
    public static void organisateur() {
        boolean out = false;
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
            System.out.println("*  9. Se déconnecter                   *");
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
                    creerTournoi();
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
                    ihm.unlog();
                    break;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
                e.printStackTrace();
            }
            //Si out est vrai, on revient à la fenêtre de connexion
            if(out) break;
        }
    }
    
    public static void arbitre() {
        boolean out = false;
        while (true) {
            System.out.println("****************************************");
            System.out.println("*              TOURNOI                 *");
            System.out.println("****************************************");
            System.out.println("*  1. Entrer le résultat du match      *");
            System.out.println("*                                      *");
            System.out.println("*  9. Se déconnecter                   *");
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
                    ihm.unlog();
                    break;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
                e.printStackTrace();
            }
          //Si out est vrai, on revient à la fenêtre de connexion
            if(out) break;
        }
    }
    
    
    public static void joueur() {
        boolean out = false;
        while (true) {
            System.out.println("****************************************");
            System.out.println("*              TOURNOI                 *");
            System.out.println("****************************************");
            System.out.println("*  1. Prochain match                   *");
            System.out.println("*  2. Tournois en cours                *");
            System.out.println("*                                      *");
            System.out.println("*  9. Se déconnecter                   *");
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
                    ihm.unlog();
                    out = true;
                    break;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
                e.printStackTrace();
            }
            //Si out est vrai, on revient à la fenêtre de connexion
            if(out) break;
        }
    }
    
    
    /**
     * Permet à l'organisateur de créer un tournoi
     * 
     */
    public static void creerTournoi() {
        System.out.println("Voici les épreuves disponibles :");
        while(true){
            ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
            ArrayList<Equipe> equipes = new ArrayList<Equipe>();
            // On récolte les épreuves
            try{
                epreuves = Ihm.recolterEpreuves();
            }
            catch(Exception e){
                System.out.print("Vos épreuves sont invalides !");
                creerTournoi();
            }
            System.out.println(epreuves);
            
            // On récolte les équipes
            try{
                equipes = Ihm.recolterEquipes();
            }
            catch(Exception e){
                System.out.print("Vos équipes sont invalides !");
                creerTournoi();
            }
            System.out.println(equipes);
            
            try {
                System.out.println("Entrez le nom du tournoi :");
                Scanner s = new Scanner(System.in);
                Moteur.creerTournoi(s.nextLine(), equipes, epreuves);
            } catch (Exception e) {
                System.out.print("La création du tournoi a échoué.");
                creerTournoi();
            }
            
            System.out.println("Tournoi créé.\n");
            return;
        }
    }

}
