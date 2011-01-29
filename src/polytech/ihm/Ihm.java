package polytech.ihm;

import java.util.ArrayList;
import java.util.Scanner;

import polytech.jtournoi.Equipe;
import polytech.jtournoi.TypeEpreuve;
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
    
    /**
     * Permet de récolter les épreuves et de les vérifier
     * @return epreuves
     *                  La liste des épreuves valide
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
     * @return equipes
     *                  La liste des équipes valides
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
                if(Stock.getEquipe().get(j).getId() == equipesId.get(i)) equipes.add(Stock.getEquipe().get(j));
            }
        }
        return equipes;
    }
    
    
    /**
     * Permet de découper une chaine selon les espaces et de renvoyer une liste d'entiers
     * @return id
     *                  Les entiers initialement séparés par des espaces dans la chaine
     * @param lue
     *                  La chaine initiale
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
    
}