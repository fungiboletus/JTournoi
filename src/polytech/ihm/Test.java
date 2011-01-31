package polytech.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import polytech.personnes.Arbitre;
import polytech.personnes.Joueur;
import polytech.stock.*;
import polytech.tools.Tools;
import polytech.exception.ArbitreDejaExistant;
import polytech.exception.EpreuveDejaExistanteException;
import polytech.exception.EpreuveInexistante;
import polytech.exception.EquipeInexistante;
import polytech.exception.ListeVideException;
import polytech.exception.NombreDeParticipantInsufisantException;
import polytech.exception.TournoiNonLanceException;
import polytech.exception.nbrArbitreInsufisantException;
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
        GeneratePlayer.generer();
        // Stock.chargerStock(TypeChargement.SQL);
        Moteur.init();
        // On log la personne qui veut se logger
        while (true) {
            while (ihm.loggedName.equals("")) {
                System.out.println("Entrez votre nom :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                ihm.log(lue);
            }
            System.out.println("Bonjour, " + ihm.loggedName
                    + ", vous êtes correctement loggé.");

            // On lance l'interface selon l'utilisateur
            if (ihm.loggedType.equals("organisateur")) {
                organisateur();
            } else if (ihm.loggedType.equals("arbitre")) {
                arbitre();
            } else if (ihm.loggedType.equals("joueur")) {
                joueur();
            }
        }
    }

    /**
     * Interface organisateur
     */
    public static void organisateur() {
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
                    creerEquipe();
                    break;
                case 2:
                    gererEquipe();
                    break;
                case 3:
                    creerTournoi();
                    break;
                case 4:
                    gererTournoi();
                    break;
                case 5:
                    creerEpreuve();
                    break;
                case 6:
                    supprimerTypeEpreuve();
                    break;
                case 7:
                    creerArbitre();
                    break;
                case 8:
                    supprimerArbitre();
                    break;
                case 9:
                    ihm.unlog();
                    return;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
            }
        }
    }

    /**
     * Interface arbitre
     */
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
                    entrerResultat();
                    break;
                case 9:
                    ihm.unlog();
                    return;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
            }
            // Si out est vrai, on revient à la fenêtre de connexion
            if (out)
                break;
        }
    }

    /**
     * Interface joueur
     */
    public static void joueur() {
        while (true) {
            System.out.println("****************************************");
            System.out.println("*              TOURNOI                 *");
            System.out.println("****************************************");
            System.out.println("*  1. Tournois en cours                *");
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
                    tournoiEnCours();
                    break;
                case 9:
                    ihm.unlog();
                    return;
                case 0:
                    Stock.enregistrerStock();
                    System.exit(0);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ce que vous avez rentré n'est pas valide.");
            }
        }
    }

    /**
     * Permet d'afficher le tournoi en cours
     */
    public static void tournoiEnCours() {
        System.out.println(Moteur.listeTournoi.get(0));
    }

    /**
     * Permet à l'arbitre d'entrer le résultat de son match en cours.
     */
    public static void entrerResultat() {
        Arbitre a = new Arbitre();
        // On cherche l'objet arbitre de l'arbitre connecté
        for (int i = 0; i < Stock.getArbitres().size(); i++) {
            if (Stock.getArbitres().get(i).getNom().equals(ihm.loggedName))
                a = Stock.getArbitres().get(i);
        }
        if (ihm.getTournoi(a).size() > 0) {
            Tournoi t = ihm.getTournoi(a).get(0);
            Match m = new Match();
            try {
                m = Moteur.getMatch(a, t);
            } catch (TournoiNonLanceException e1) {
                System.out.println("Le tournoi n'est pas encore lancé.");
            }
            // Résultat équipe 1
            System.out.println("Veuillez entrer le score de l'équipe "
                    + m.getE1().getNom());
            Scanner s;
            int score1 = 0, score2 = 0;
            int place = 0;
            while (true) {
                try {
                    s = new Scanner(System.in);
                    score1 = s.nextInt();
                } catch (Exception e) {
                    System.out
                            .println("La syntaxe que vous avez entrée est incorrecte.");
                    continue;
                }
                break;
            }
            // Résultat équipe 2
            System.out.println("Veuillez entrer le score de l'équipe "
                    + m.getE2().getNom());
            while (true) {
                try {
                    s = new Scanner(System.in);
                    score2 = s.nextInt();
                } catch (Exception e) {
                    System.out
                            .println("La syntaxe que vous avez entrée est incorrecte.");
                    continue;
                }
                break;
            }
            try {
                // Pour chaque épreuve
                for (int i = 0; i < t.getEpreuves().size(); i++) {
                    // Pour chaque match
                    for (int j = 0; j < t.getEpreuves().get(j)
                            .getCurrentMatch().size(); j++) {
                        if (t.getEpreuves().get(i).getCurrentMatch().get(j)
                                .equals(m)) {
                            place = i;
                            break;
                        }
                    }
                }

                t.getEpreuves().get(place).setScore(a, m, score1, score2);
            } catch (nbrArbitreInsufisantException e) {
                System.out.println("Nombre d'arbitres insuffisant.");
            } catch (TournoiNonLanceException e) {
                System.out.println("Tournoi pas encore lancé.");
            }
            System.out
                    .println("Vous avez bien entré les résultats de ce match");
        } else {
            System.out.println("Vous n'arbitrez aucun match actuellement...");
        }
    }

    /**
     * Permet de créer une équipe
     */
    public static void creerEquipe() {
        Scanner s;
        String lue = "";
        boolean verif;
        while (true) {
            verif = true;
            System.out.print("Nom de l'équipe (-1 pour revenir) : ");
            s = new Scanner(System.in);
            lue = s.nextLine();
            if (lue.equals("") || lue.equals("-1"))
                return;
            // On bérifie que l'équipe n'existe pas déjà
            for (int i = 0; i < Stock.getEquipe().size(); i++) {
                if (Stock.getEquipe().get(i).getNom().equals(lue)) {
                    System.out.println("Cette équipe existe déjà.");
                    verif = false;
                    break;
                }
            }
            if (verif)
                break;
        }
        Equipe eq = new Equipe(lue);
        Stock.addEquipe(eq);
        System.out.println("Votre équipe a bien été créée");

        System.out
                .println("Vous allez maintenant entrer les joueurs de l'équipe.");
        System.out
                .println("N'oubliez pas : le total des compétences doit être au moins de 5 pour entrer ensuite vos 5 épreuves.");
        ajouteJoueurs(eq.getId());
        ajouterEpreuves(eq.getId());
    }

    /**
     * Permet de gérer les épreuves d'une équipe
     * 
     * @param id
     *            de l'équipe
     */
    public static void ajouterEpreuves(int id) {
        ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
        // On vérifie qu'il y a bien au moins 5 compétences dans l'équipe
        int nb = 0;
        // Pour chaque personne de l'équipe
        for (int i = 0; i < Stock.getEquipeParId(id).getMembres().size(); i++) {
            // On ajoute les compétences
            nb += Stock.getEquipeParId(id).getMembres().get(i).getCompetences()
                    .size();
        }
        if (nb >= 5) {
            while (true) {
                try {
                    epreuves = Ihm.recolterEpreuves();
                } catch (Exception e) {
                    System.out.println("Ce que vous avez entré est incorrect.");
                    continue;
                }
                if (epreuves.size() != 5) {
                    System.out.println("Il faut exactement 5 épreuves.");
                } else
                    break;
            }
            Stock.getEquipeParId(id).setEpreuves(epreuves);
            System.out
                    .println("Les épreuves de l'équipe ont bien été ajoutées");
        } else {
            System.out
                    .println("Il y a moins de 5 bcompétences dans l'équipe, ajoutez des joueurs d'abord.");
        }
    }

    /**
     * Permet de gérer une équipe.
     */
    public static void gererEquipe() {
        while (true) {
            if (Stock.getEquipe().size() > 0)
                System.out.println("Voici les équipes :");
            for (int i = 0; i < Stock.getEquipe().size(); i++) {
                System.out.println("\t" + Stock.getEquipe().get(i));
            }
            System.out
                    .print("L'id de l'équipe que vous voulez gérer (-1 pour revenir au menu précédent): ");
            Scanner s = new Scanner(System.in);
            String lue = s.nextLine();
            int a = -1;
            if (lue.equals("-1"))
                break;
            for (int i = 0; i < Stock.getEquipe().size(); i++) {
                if (Stock.getEquipe().get(i).getId() == Integer.parseInt(lue)) {
                    a = i;
                    break;
                }
            }
            if (a != -1) {
                if (!lue.equals("-1")) {
                    while (true) {
                        System.out
                                .println("****************************************");
                        System.out
                                .println("*           GERER UNE EQUIPE           *");
                        System.out
                                .println("****************************************");
                        System.out
                                .println("*  1. Ajouter des joueurs              *");
                        System.out
                                .println("*  2. Supprimer des joueurs            *");
                        System.out
                                .println("*  3. Supprimer l'équipe               *");
                        System.out
                                .println("*  4. Afficher l'équipe                *");
                        System.out
                                .println("*                                      *");
                        System.out
                                .println("*  0. Revenir au menu précédent        *");
                        System.out
                                .println("****************************************");
                        System.out.print("Votre choix: ");
                        try {
                            s = new Scanner(System.in);
                            int lue1 = s.nextInt();
                            switch (lue1) {
                            case 0:
                                return;
                            case 1:
                                ajouteJoueurs(Integer.parseInt(lue));
                                break;
                            case 2:
                                supprimeJoueurs(Integer.parseInt(lue));
                                break;
                            case 3:
                                if (supprimeEquipe(Integer.parseInt(lue)))
                                    return;
                            case 4:
                                System.out.println(Stock.getEquipe().get(a)
                                        .toString2());
                                break;
                            }

                        } catch (Exception e) {
                            System.out
                                    .println("Ce que vous avez rentré n'est pas valide.");
                        }
                    }
                }
            } else
                System.out.println("Cette équipe n'existe pas.");
        }
    }

    /**
     * Permet d'ajouter un joueur
     * 
     * @param id
     *            l'id de l'équipe
     */
    public static void ajouteJoueurs(int id) {
        int index = -1;
        for (int i = 0; i < Stock.getEquipe().size(); i++) {
            if (Stock.getEquipe().get(i).getId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Joueur j;
            Scanner s;
            String lue;
            boolean verif;
            TypeEpreuve epreuve;
            while (true) {
                // Informations du joueur
                System.out
                        .print("Nom prenom password du joueur séparés par des espaces (-1 pour arrêter): ");
                s = new Scanner(System.in);
                lue = s.nextLine();
                if (ihm.cutStringBySpace2(lue).size() < 3 && !lue.equals("-1")) {
                    System.out
                            .println("La syntaxe que vous avez entré n'est pas correcte");
                    continue;
                }
                if (!lue.equals("-1")) {
                    j = new Joueur(ihm.cutStringBySpace2(lue).get(0), ihm
                            .cutStringBySpace2(lue).get(1), ihm
                            .cutStringBySpace2(lue).get(2));
                    // Compétences du joueur
                    while (true) {
                        int lu = Ihm.demanderInt("Id de la compétence du joueur (-1 pour arrêter): ");
                        verif = false;
                        epreuve = new TypeEpreuve();
                        for (int i = 0; i < Stock.getTypesEpreuves().size(); i++) {
                            if (Stock.getTypesEpreuves().get(i).getId() == lu) {
                                verif = true;
                                epreuve = Stock.getTypesEpreuves().get(i);
                                break;
                            }
                        }
                        if (lue.equals(""))
                            continue;
                        if (!lue.equals("-1")) {
                            if (verif) {
                                j.addCompetence(epreuve);
                            } else {
                                System.out
                                        .println("Cet id de type d'épreuve n'est pas dans la liste des épreuves : ");
                                for (int i = 0; i < Stock.getTypesEpreuves()
                                        .size(); i++)
                                    System.out.println("\t "
                                            + Stock.getTypesEpreuves().get(i));
                                continue;
                            }
                        } else
                            break;
                    }
                    //On supprime les doublons
                    j.setCompetences(Tools.supprimerDouble(j.getCompetences()));
                    Stock.getEquipe().get(index).ajouterParticipant(j);
                    System.out.println("Le joueur " + j.getNom() + " "
                            + j.getPrenom() + " a bien été ajouté.");
                } else
                    break;
            }
        }
    }

    /**
     * Permet de supprimer un joueur.
     * 
     * @param id
     *            l'id de l'équipe du joueur à supprimer.
     */
    public static void supprimeJoueurs(int id) {
        while (true) {
            Scanner s;
            s = new Scanner(System.in);
            int index = -1;
            Equipe e;
            for (int i = 0; i < Stock.getEquipe().size(); i++) {
                if (Stock.getEquipe().get(i).getId() == id) {
                    index = i;
                    e = Stock.getEquipe().get(i);
                    break;
                }
            }
            // Si il y a des membres dans l'équipe
            if (Stock.getEquipe().get(index).getMembres().size() > 0) {
                System.out.println("Liste des joueurs: ");
                for (int i = 0; i < Stock.getEquipe().get(index).getMembres()
                        .size(); i++) {
                    System.out.println("\t"
                            + Stock.getEquipe().get(index).getMembres().get(i)
                                    .toString2());
                }
                System.out
                        .print("L'id du joueur que vous voulez supprimer (-1 pour sortir): ");
                int lu;
                try {
                    lu = s.nextInt();
                } catch (Exception ex) {
                    System.out.println("Syntaxe incorrecte.");
                    continue;
                }
                if (!(lu == -1)) {
                    if (Stock.getEquipe().get(index).supprimerJoueur(lu)) {
                        System.out.println("Joueur supprimé.");
                        break;
                    } else
                        System.out
                                .println("Ce joueur n'existe pas dans l'équipe "
                                        + Stock.getEquipe().get(index).getNom()
                                        + ".");
                } else
                    return;
            }
        }
    }

    /**
     * Permet de supprimer une équipe.
     * 
     * @param id
     *            id de l'équipe à supprimer
     * @return Si la suppression a réussi ou non.
     */
    public static boolean supprimeEquipe(int id) {
        Scanner s;
        String equipe = "";
        s = new Scanner(System.in);
        int index = -1;
        for (int i = 0; i < Stock.getEquipe().size(); i++) {
            if (Stock.getEquipe().get(i).getId() == id) {
                index = i;
                equipe = Stock.getEquipe().get(i).getNom();
                break;
            }
        }
        while (true) {
            System.out.print("Vous êtes sûr ? (y/n) ");
            String lue = s.nextLine();
            if (lue.equals("y")) {
                Stock.getEquipe().remove(index);
                System.out.println("Equipe " + equipe + " supprimée.");
                return true;
            } else if (lue.equals("n")) {
                System.out.println("Suppression annulée.");
                return false;
            }
            System.out.println("Veuillez rentrer y ou n.");
        }
    }

    /**
     * Permet à l'organisateur de créer un tournoi
     * 
     */
    private static void creerTournoi() {
        System.out.println("Voici les épreuves disponibles :");
        while (true) {
            ArrayList<TypeEpreuve> epreuves = new ArrayList<TypeEpreuve>();
            ArrayList<Equipe> equipes = new ArrayList<Equipe>();
            // On récolte les épreuves
            try {
                epreuves = Ihm.recolterEpreuves();
            } 
            catch (EpreuveInexistante e) {
                System.out.print("Ces équipes n'existent pas toutes.");
                return;
            }
            catch (ListeVideException e) {
                System.out.print("La liste d'épreuves est vide...");
                return;
            }
            catch (Exception e) {
                System.out.print("Vos épreuves sont invalides !");
                return;
            }

            // On récolte les équipes
            try {
                equipes = Ihm.recolterEquipes();
            } 
            catch (EquipeInexistante e) {
                System.out.print("Ces équipes n'existent aps toutes !");
                return;
            }
            catch (ListeVideException e) {
                System.out.print("La liste d'équipes est vide...");
                return;
            }
            catch (Exception e) {
                System.out.print("Vos équipes sont invalides !");
                return;
            }

            try {
                System.out.println("Entrez le nom du tournoi :");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                Tournoi tournoi = Moteur.creerTournoi(lue, epreuves);

                // On demande le joueur dans le cas des épreuves individuelles
                for (int i = 0; i < epreuves.size(); i++) {
                    HashMap<Equipe, Joueur> map = new HashMap<Equipe, Joueur>();
                    // Pour les épreuves individuelles
                    if (epreuves.get(i).isIndivideul()) {
                        for (Equipe eq : equipes) {
                            // Si l'épreuve est aussi dans la liste des épreuves
                            // de l'équipe
                            if (eq.getEpreuves().contains(epreuves.get(i))) {
                                ArrayList<Joueur> joueursCompetents = ihm
                                        .getJoueursCompetents(eq, epreuves
                                                .get(i));
                                // Si un seul joueur convient, on le choisit
                                // directement
                                if (joueursCompetents.size() == 1) {
                                    map.put(eq, joueursCompetents.get(0));
                                }
                                // Sinon on demandera un choix à l'organisateur
                                else {
                                    Joueur j = new Joueur();
                                    // On vérifie que le joueur est bien
                                    // compétent pour cette épreuve
                                    while (!j.getCompetences().contains(
                                            epreuves.get(i))) {
                                        System.out
                                                .println("Entrez le nom du joueur de l'équipe "
                                                        + eq.getNom()
                                                        + " pour l'épreuve "
                                                        + epreuves.get(i)
                                                                .getNom());
                                        System.out
                                                .println("Vous pouvez choisir parmi :");
                                        String sJ = "";
                                        for (int k = 0; k < joueursCompetents
                                                .size(); k++) {
                                            sJ += joueursCompetents.get(k)
                                                    .getNom()
                                                    + " ";
                                        }
                                        System.out.println(sJ);
                                        s = new Scanner(System.in);
                                        lue = s.nextLine();
                                        j = ihm.getJoueurByName(lue, eq);
                                        map.put(eq, j);
                                    }
                                }
                            }
                        }
                    }
                    // Pour les épreuves collectives
                    else {
                        for (Equipe eq : equipes) {
                            if (eq.getEpreuves().contains(epreuves.get(i))) {
                                map.put(eq, null);
                            }
                        }
                    }
                    Moteur.setEpreuve(tournoi, epreuves.get(i), map);
                }
                Moteur.startTournoi(tournoi);

            } catch (NombreDeParticipantInsufisantException e) {
                System.out
                        .println("Le nombre de participants est insuffisant.");
                return;
            } catch (EpreuveDejaExistanteException e) {
                System.out.println("L'épreuve existe déjà.");
                return;
            } catch (Exception e) {
                System.out.println("La création du tournoi a échoué.");
                //System.out.println(e);
                e.printStackTrace();
                return;
            }

            System.out.println("Tournoi créé.\n");
            return;
        }
    }

    public static void gererTournoi() {
        
        while (true) {
            if (!(Moteur.getTournois().size() > 0)){
                System.out.println("Il n'y a aucun tournoi.");
                return;
            }
            System.out.println("Voici les tournois :");
            for (int i = 0; i < Moteur.getTournois().size(); i++) {
                System.out.println("\t" + Moteur.getTournois().get(i));
            }
            int lue = Ihm.demanderInt("L'id du tournoi que vous voulez gérer (-1 pour revenir au menu précédent): ");
            int a = -1;
            if (lue == -1)
                break;
            for (int i = 0; i < Moteur.getTournois().size(); i++) {
                if (Moteur.getTournois().get(i).getId() == lue) {
                    a = i;
                    break;
                }
            }
            if (a != -1) {
                if (!(lue == -1)) {
                    while (true) {
                        System.out
                                .println("****************************************");
                        System.out
                                .println("*           GERER UN TOURNOI           *");
                        System.out
                                .println("****************************************");
                        System.out
                                .println("*  1. Afficher les gagnants            *");
                        System.out
                                .println("*  2. Afficher le tournoi              *");
                        System.out
                                .println("*                                      *");
                        System.out
                                .println("*  0. Revenir au menu précédent        *");
                        System.out
                                .println("****************************************");
                        System.out.print("Votre choix: ");
                        try {
                            Scanner s = new Scanner(System.in);
                            int lue1 = s.nextInt();
                            switch (lue1) {
                            case 0:
                                return;
                            case 1:
                                //ajouteJoueurs(lue);
                                break;
                            case 2:
                                System.out.println(Moteur.listeTournoi.get(lue));
                                break;
                            }

                        } catch (Exception e) {
                            System.out
                                    .println("Ce que vous avez rentré n'est pas valide.");
                        }
                    }
                }
            } else
                System.out.println("Cette équipe n'existe pas.");
        }
        
    }

    /**
     * Permet de créer un type d'épreuve
     */
    public static void creerEpreuve() {
        Scanner s;
        String nom, type, indi = "";
        boolean individuel;
        int duree = 0, points = 0;
        boolean v = true, v2 = true;
        while (true) {
            System.out
                    .print("Nom de l'épreuve (-1 pour revenir au menu précédent): ");
            s = new Scanner(System.in);
            nom = s.nextLine();
            if (nom.equals("-1"))
                return;
            System.out
                    .print("Type de l'épreuve (-1 pour revenir au menu précédent): ");
            s = new Scanner(System.in);
            type = s.nextLine();
            if (nom.equals("-1"))
                return;
            duree = Ihm
                    .demanderInt("Durée de l'épreuve (-1 pour changer le nom du sport): ");
            if (duree == -1)
                return;
            points = Ihm
                    .demanderInt("Nombre de points de l'épreuve (-1 pour changer le nom du sport): ");
            if (points == -1)
                return;
            while (true) {
                System.out.print("Est-ce une épreuve individuelle ? (y/n)");
                s = new Scanner(System.in);
                indi = s.nextLine();
                if (indi.equals("-1"))
                    return;
                if (indi.equals("y")) {
                    individuel = true;
                    break;
                } else if (indi.equals("n")) {
                    individuel = false;
                    break;
                }
                System.out.println("Veuillez rentrer y ou n.");
            }
            // On recueille l'id le plus haut
            int idHaut = 0;
            for (int i = 0; i < Stock.getTypesEpreuves().size(); i++) {
                if (Stock.getTypesEpreuves().get(i).getId() > idHaut)
                    idHaut = Stock.getTypesEpreuves().get(i).getId();
            }
            Stock.addTypesEpreuve(new TypeEpreuve((idHaut + 1), nom, type,
                    duree, points, individuel));
            System.out.println("L'épreuve " + nom
                    + " ajoutée la liste des épreuves.");
        }
    }

    /**
     * Permet de supprimer un type d'épreuve.
     */
    public static void supprimerTypeEpreuve() {
        Scanner s;
        s = new Scanner(System.in);
        int epreuve = 0, index = 0;
        while (true) {
            System.out.println("Voici la liste des épreuves :");
            for (int i = 0; i < Stock.getTypesEpreuves().size(); i++)
                System.out.println("\t " + Stock.getTypesEpreuves().get(i));
            boolean loop = true;
            while (loop) {
                epreuve = Ihm
                        .demanderInt("Entrez l'id de l'épreuve que vous voulez supprimer (-1 pour revenir au menu précédent): ");
                if (epreuve == -1)
                    return;
                for (int i = 0; i < Stock.getTypesEpreuves().size(); i++) {
                    if (Stock.getTypesEpreuves().get(i).getId() == epreuve) {
                        loop = false;
                        index = i;
                        break;
                    }
                }
            }
            // On demande confirmation avant suppression
            while (true) {
                System.out.print("Vous êtes sûr ? (y/n) ");
                String lue = s.nextLine();
                if (lue.equals("y")) {
                    Stock.getTypesEpreuves().remove(index);
                    System.out.println("Epreuve [" + epreuve + "] supprimé.");
                    return;
                } else if (lue.equals("n")) {
                    System.out.println("Suppression annulée.");
                    return;
                }
                System.out.println("Veuillez rentrer y ou n.");
            }
        }
    }

    /**
     * Permet de créer des arbitres.
     */
    public static void creerArbitre() {
        Scanner s;
        String nom;
        Arbitre a;
        TypeEpreuve epreuve;
        boolean verif;
        while (true) {
            System.out.print("Nom prenom password de l'arbitre séparés par des espaces (-1 pour arrêter): ");
            s = new Scanner(System.in);
            nom = s.nextLine();
            if (ihm.cutStringBySpace2(nom).size() < 3 && !nom.equals("-1")) {
                System.out.println("La syntaxe que vous avez entré n'est pas correcte");
                continue;
            }
            if (!nom.equals("-1")) {
                a = new Arbitre(ihm.cutStringBySpace2(nom).get(0), ihm
                        .cutStringBySpace2(nom).get(1), ihm
                        .cutStringBySpace2(nom).get(2));
             // Compétences de l'arbitre
                while (true) {
                    int lu = Ihm.demanderInt("Id de la compétence de l'arbitre (-1 pour arrêter): ");
                    verif = false;
                    epreuve = new TypeEpreuve();
                    for (int i = 0; i < Stock.getTypesEpreuves().size(); i++) {
                        if (Stock.getTypesEpreuves().get(i).getId() == lu) {
                            verif = true;
                            epreuve = Stock.getTypesEpreuves().get(i);
                            break;
                        }
                    }
                    if (!(lu == -1)) {
                        if (verif) {
                            a.addCompetence(epreuve);
                        } else {
                            System.out
                                    .println("Cet id de type d'épreuve n'est pas dans la liste des épreuves : ");
                            for (int i = 0; i < Stock.getTypesEpreuves()
                                    .size(); i++)
                                System.out.println("\t "
                                        + Stock.getTypesEpreuves().get(i));
                            continue;
                        }
                    } else
                        break;
                }
                try {
                    //On supprime les doublons
                    a.setCompetences(Tools.supprimerDouble(a.getCompetences()));
                    Stock.addArbitre(a);
                    System.out.println("Arbitre " + nom
                            + " ajouté à la liste des arbitres.");
                    return;
                } catch (ArbitreDejaExistant e) {
                    System.out.println("Cet arbitre existe déjà.");
                }
                
            } else
                return;
        }
    }

    /**
     * Permet de supprimer un arbitre.
     */
    public static void supprimerArbitre() {
        int arbitre = 0, index = 0;
        while (true) {
            // Liste des arbitres pour choix
            for (int i = 0; i < Stock.getArbitres().size(); i++) {
                System.out.println("\t"
                        + Stock.getArbitres().get(i)
                                .toString2());
            }
            boolean loop = true;
            while (loop) {
                arbitre = Ihm.demanderInt("Entrez l'id de l'arbitre que vous voulez supprimer (-1 pour revenir au menu précédent): ");
                if (arbitre == -1)
                    return;
                for (int i = 0; i < Stock.getArbitres().size(); i++) {
                    if (Stock.getArbitres().get(i).getId() == arbitre) {
                        loop = false;
                        index = i;
                        break;
                    }
                }
            }
            while (true) {
                System.out.print("Vous êtes sûr ? (y/n) ");
                Scanner s = new Scanner(System.in);
                String lue = s.nextLine();
                if (lue.equals("y")) {
                    Stock.getArbitres().remove(index);
                    System.out.println("Arbitre [" + arbitre + "] supprimé.");
                    return;
                } else if (lue.equals("n")) {
                    System.out.println("Suppression annulée.");
                    return;
                }
                System.out.println("Veuillez rentrer y ou n.");
            }

        }
    }

}
