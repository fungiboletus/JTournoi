package polytech.ihm;
import java.util.ArrayList;
import java.util.Scanner;

import polytech.tools.Tools;

import polytech.jtournoi.*;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>(18);
		list.add("a");
		list.add(null);
		System.out.println(list.size());
	}

}


class Main {

    // static ArrayList<Equipe> equipes = new ArrayList<Equipe>();
    // static ArrayList<Sport> sports = new ArrayList<Sport>();
    // static ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();
    // static ArrayList<Organisateur> organisateurs = new
    // ArrayList<Organisateur>();
    // static ArrayList<Tournoi> tournois = new ArrayList<Tournoi>();

    // L'objet de stockage
    // static Xml xml = new Xml();
    // Le nom de la personne loggée
    static int loggedType;
    static String loggedName = "";

    // Fonction principale
    public static void main(String[] args) {
        while (loggedName.equals("")) {
            System.out.println("Entrez votre nom :");
            Scanner s = new Scanner(System.in);
            String lue = s.nextLine();
            // On vérifie si le nom est celui d'un organisateur
            for (int i = 0; i < users.size; i++) {
                if (lue.equals(users.get(i).getName())) {
                    // On demande le mot de passe
                    System.out.println("Entrez votre mot de passe :");
                    Scanner s2 = new Scanner(System.in);
                    String lue2 = s.nextLine();
                    // Si tout est bon, l'utilisateur est loggé
                    if (lue2.equals(users.get(i).getPass())) {
                        loggedName = users.get(i).getName();
                        loggedType = users.get(i).getType();
                    }
                    break;
                }
            }
        }
    }

    // L'écran de l'organisateur
    public static void organisateur() {
        /*
         * // On charge equipes.xml dans l'arraylist equipes locale equipes =
         * xml.getEquipes(); sports = xml.getSports(); arbitres =
         * xml.getArbitres(); tournois = xml.getTournois();
         */
        while (true) {
            System.out.println("****************************************");
            System.out.println("*              MINI-PROJET             *");
            System.out.println("****************************************");
            System.out.println("*  1. Créer une équipe                 *");
            System.out.println("*  2. Gérer une équipe                 *");
            System.out.println("*  3. Créer un tournoi                 *");
            System.out.println("*  4. Gérer un tournoi                 *");
            System.out.println("*  5. Créer un sport                   *");
            System.out.println("*  6. Supprimer un sport               *");
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
}
