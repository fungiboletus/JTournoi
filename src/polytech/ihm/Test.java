package polytech.ihm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import polytech.personnes.Joueur;
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
        Stock.chargerStock(TypeChargement.SQL);

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
            HashMap<Equipe,Joueur> equipes = new HashMap<Equipe,Joueur>();
            // On récolte les épreuves
            try{
                epreuves = Ihm.recolterEpreuves();
            }
            catch(Exception e){
                System.out.print("Vos épreuves sont invalides !");
                return;
            }
            
            // On récolte les équipes
            try{
                equipes = Ihm.recolterEquipes();
            }
            catch(Exception e){
                System.out.print("Vos équipes sont invalides !");
                return;
            }
            
            // On demande le joueur dans le cas des épreuves individuelles
            for(int i=0; i<epreuves.size(); i++){
                if(epreuves.get(i).isIndivideul()){
                    for(Equipe eq : equipes.keySet()){
                        ArrayList<Joueur> joueursCompetents = ihm.getJoueursCompetents(eq, epreuves.get(i));
                        //Si un seul joueur convient, on le choisit directement
                        if(joueursCompetents.size()==1){
                            equipes.put(eq, joueursCompetents.get(0));
                        }
                        //Sinon on demandera un choix à l'organisateur
                        else {
                            Joueur j = new Joueur();
                            // On vérifie que le joueur est bien compétent pour cette épreuve
                            while(!j.getCompetences().contains(epreuves.get(i))){
                                System.out.println("Entrez le nom du joueur de l'équipe "+eq.getNom()+" pour l'épreuve "+epreuves.get(i).getNom());
                                System.out.println("Vous pouvez choisir parmi :");
                                String sJ = "";
                                for(int k=0; k<joueursCompetents.size(); k++){
                                    sJ += joueursCompetents.get(i).getNom()+" ";
                                }
                                System.out.println(sJ);
                                Scanner s = new Scanner(System.in);
                                String lue = s.nextLine();
                                j = ihm.getJoueurByName(lue, eq);
                                equipes.put(eq, j);
                            }
                        }
                    }
                }
            }
            
            try {
                System.out.println("Entrez le nom du tournoi :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                //Moteur.creerTournoi(lue, equipes, epreuves);
            } catch (Exception e) {
                System.out.print("La création du tournoi a échoué.");
                return;
            }
            
            System.out.println("Tournoi créé.\n");
            return;
        }
    }

}
