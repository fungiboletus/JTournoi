package polytech.ihm;

import java.util.ArrayList;
import java.util.Scanner;

import polytech.exception.TournoiNonLanceException;
import polytech.jtournoi.Equipe;
import polytech.jtournoi.Moteur;
import polytech.jtournoi.Tournoi;
import polytech.jtournoi.TypeEpreuve;
import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
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
     * @param nom Nom de log
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
                if (Stock.getOrganisateurs().get(i).identicalPassword(lue)) {
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
                    if (Stock.getArbitres().get(i).identicalPassword(lue)) {
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
                    if (Stock.getJoueurs().get(i).identicalPassword(lue)) {
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
    
    /**
     * Permet de récolter les épreuves et de les vérifier
     * @return epreuves La liste des épreuves valide
     */
    public static ArrayList<TypeEpreuve> recolterEpreuves() throws Exception{
        ArrayList<Integer> epreuvesId = new ArrayList<Integer>();
        ArrayList<TypeEpreuve> typeEpreuves = new ArrayList<TypeEpreuve>();
        Scanner s;
        //On liste les types d'épreuves possibles
        for (int i = 0; i < Stock.getTypesEpreuves().size(); i++)
            System.out.println("\t " + Stock.getTypesEpreuves().get(i));
        // Phase de récolte des épreuves
        System.out.print("Entrez le numéro des épreuves choisies séparés par des espaces :");
        s = new Scanner(System.in);
        String lue = s.nextLine();
        epreuvesId = cutStringBySpace(lue);
        //Phase de vérification des épreuves récoltées
        //On vérifie que la liste d'épreuve n'est pas vide
        if(epreuvesId.size() == 0) throw new Exception();
        
        //On vérifie que chacune des épreuves existent
        for(int i=0; i<epreuvesId.size(); i++){
            boolean contient = false;
            for(int j=0; j<Stock.getTypesEpreuves().size(); j++){
                if(Stock.getTypesEpreuves().get(j).getId() == epreuvesId.get(i)) contient = true;
            }
            if(!contient) throw new Exception();
        }
        // On construit la liste de Type d'Epreuves
        for(int i=0; i<epreuvesId.size(); i++){
            for(int j=0; j<Stock.getTypesEpreuves().size(); j++){
                if(Stock.getTypesEpreuves().get(j).getId() == epreuvesId.get(i)) typeEpreuves.add(Stock.getTypesEpreuves().get(j));
            }
        }
        return typeEpreuves;
    }
    
    /**
     * Permet de récolter les équipes et de les vérifier
     * @return equipes La liste des équipes valides
     */
    public static ArrayList<Equipe> recolterEquipes() throws Exception{
        ArrayList<Integer> equipesId = new ArrayList<Integer>();
        ArrayList<Equipe> equipes = new ArrayList<Equipe>();
        Scanner s;
        //On liste les équipes possibles
        for (int i = 0; i < Stock.getEquipe().size(); i++)
            System.out.println("\t " + Stock.getEquipe().get(i));
        // Phase de récolte des épreuves
        System.out.print("Entrez le numéro d'identifiant des équipes que vous incluez, séparés par des espaces :");
        s = new Scanner(System.in);
        String lue = s.nextLine();
        equipesId = cutStringBySpace(lue);
        //Phase de vérification des équipes récoltées
        //On vérifie que la liste d'épreuve n'est pas vide
        if(equipesId.size() == 0) throw new Exception();
        
        //On vérifie que chacune des équipes existent
        for(int i=0; i<equipesId.size(); i++){
            boolean contient = false;
            for(int j=0; j<Stock.getEquipe().size(); j++){
                if(Stock.getEquipe().get(j).getId() == equipesId.get(i)) contient = true;
            }
            if(!contient) throw new Exception();
        }
        // On construit la liste des équipes à renvoyer
        for(int i=0; i<equipesId.size(); i++){
            for(int j=0; j<Stock.getEquipe().size(); j++){
                if(Stock.getEquipe().get(j).getId() == equipesId.get(i)){
                    equipes.add(Stock.getEquipe().get(j));
                }
            }
        }
        return equipes;
    }
    
    
    /**
     * Permet de découper une chaine selon les espaces et de renvoyer une liste d'entiers
     * @return id Les entiers initialement séparés par des espaces dans la chaine
     * @param lue La chaine initiale
     */
    public static ArrayList<Integer> cutStringBySpace(String lue){
        ArrayList<Integer> id = new ArrayList<Integer>();
        while(!lue.equals("")){
            int occurence = lue.indexOf(' ');
            //Si il reste des espaces
            if(occurence != -1) {
                id.add(Integer.parseInt(lue.substring(0,occurence)));
                lue = lue.substring(occurence+1, lue.length());
            }
            //Si le String lue ne contient plus d'espaces, il n'y a qu'un mot
            else{
                id.add(Integer.parseInt(lue.substring(0,lue.length())));
                lue = "";
            }
        }
        return id;
    }
    
    /**
     * Permet de découper une chaine selon les espaces et de renvoyer des chaines découpées
     * @return id Les entiers initialement séparés par des espaces dans la chaine
     * @param lue La chaine initiale
     */
    public ArrayList<String> cutStringBySpace2(String lue){
        ArrayList<String> chaine = new ArrayList<String>();
        while(!lue.equals("")){
            int occurence = lue.indexOf(' ');
            //Si il reste des espaces
            if(occurence != -1) {
                chaine.add(lue.substring(0,occurence));
                lue = lue.substring(occurence+1, lue.length());
            }
            //Si le String lue ne contient plus d'espaces, il n'y a qu'un mot
            else{
                chaine.add(lue.substring(0,lue.length()));
                lue = "";
            }
        }
        return chaine;
    }

    /**
     * Permet d'avoir un objet joueur à partir de son nom et de son équipe
     * @param nom Le nom du joueur
     * @param equipe l'équipe du joueur
     * @return new Joueur() le joueur
     */
    public Joueur getJoueurByName(String nom, Equipe equipe){
        for(int i=0; i<equipe.getNombreDeJoueurs(); i++){
            if(equipe.getJoueur(i).getNom().equals(nom)) return equipe.getJoueur(i);
        }
        return new Joueur();
    }
    
    /**
     * Permet d'avoir les joueurs d'une équipe qui ont une compétence donnée.
     * 
     * @param equipe l'équipe
     * @param type la compétence recherchée
     * @return joueurs Les joueurs
     */
    public ArrayList<Joueur> getJoueursCompetents(Equipe equipe, TypeEpreuve type){
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        for(Joueur j : equipe.getMembres()){
            if(j.getCompetences().contains(type)){
                joueurs.add(j);
            }
        }
        return joueurs;
    }
    
    /**
     * Permet d'avoir les tournois dans lequels est impliqué un arbitre.
     * @param a l'arbitre
     * @return tournois la liste des tournois
     */
    public ArrayList<Tournoi> getTournoi(Arbitre a){
        ArrayList<Tournoi> tournois = new ArrayList<Tournoi>();
        //Pour chaque tournoi
        for(int i=0; i<Moteur.listeTournoi.size(); i++){
            //Pour chaque match
            try {
                for (int j=0; j<Moteur.getCurrentMatch(Moteur.listeTournoi.get(i)).size(); j++){
                    if(Moteur.getCurrentMatch(Moteur.listeTournoi.get(i)).get(j).getArbitre().equals(a)){
                        tournois.add(Moteur.listeTournoi.get(i));
                        break;
                    }
                }
            } catch (TournoiNonLanceException e) {
                System.out.println("Tournoi pas encore lancé.");
            }
        }
        return tournois;
    }
    
    /**
     * Permet de demander un entier à l'utilisateur.
     * @param message le message à afficher avant la demande.
     * @return nb le nombre entré par l'utilisateur.
     */
    public static int demanderInt(String message){
        int nb;
        while (true) {
            try {
                System.out.print(message);
                Scanner s = new Scanner(System.in);
                nb = s.nextInt();
                if (nb == -1) return -1;
                break;
            } catch (Exception e) {
                System.out.println("Veuillez rentrer un entier.");
            }
        }
        return nb;
    }
    
    
}