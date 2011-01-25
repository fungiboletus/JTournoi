package stockm.ihm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import stockm.exceptions.BadArgumentException;
import stockm.exceptions.BikeNotCompleteException;
import stockm.exceptions.FieldAlreadyFilledException;
import stockm.exceptions.InexistantElementException;
import stockm.exceptions.InexistantPartException;
import stockm.exceptions.OutOfStockException;
import stockm.stock.Cart;
import stockm.stock.Stock;
import stockm.types.Bike;
import stockm.types.Part;

/**
 * Provide a textual interface to an AddressBook. Different commands provide
 * access to the data in the address book.
 * 
 * One to search the address book.
 * 
 * One to allow a set of contact details to be entered.
 * 
 * One to show all the entries in the book.
 * 
 * @author David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 */
public class StockManagementTextInterface {

    private Parser parser = new Parser();
    private CommandWords cw = new CommandWords();
    private Cart current;

    /**
     * Constructor for objects of class StockManagementTextInterface
     * 
     * @param book
     *            The address book to be manipulated.
     */
    public StockManagementTextInterface() {
    }

    /**
     * Read a series of commands from the user to interact with the address
     * book. Stop when the user types 'quit'.
     */
    public void run() {
        System.out.println("STOCK MANAGEMENT");
        System.out.println("Type 'help' for a list of commands.");
        Cart c;
        ArrayList<String> command;
        do {
            Stock.checkStock();
            command = parser.getCommand();
            if (command.get(0).equals("bike")) {
                bike(command);
            } else if (command.get(0).equals("list")) {
                list(command);
            } else if (command.get(0).equals("stockprice")) {
                stockPrice();
            } else if (command.get(0).equals("help")) {
                help();
            } else if (command.get(0).equals("sellprice")) {
                sellPrice();
            } else if (command.get(0).equals("cart+")) {
                add(command);
            } else if (command.get(0).equals("cart-")) {
                remove(command);
            } else if (command.get(0).equals("cart")) {
                cart(command);
            } else if (command.get(0).equals("delete")) {
                delete();
            } else if (command.get(0).equals("buy")) {
                buy();
            } else if (command.get(0).equals("sales")) {
                sales();
            } else {
                // Do nothing.
            }
        } while (!(command.get(0).equals("quit")));

        System.out.println("Goodbye.");
    }

    private void bike(ArrayList<String> list) {
        ArrayList<Part> ret = new ArrayList<Part>();
        String s1, s2 = "";
        for (int i = 1; i < list.size(); i++) {
            if (i % 2 == 1) {
                s1 = cw.getType(list.get(i).toLowerCase());
                s2 = list.get(i + 1).toLowerCase();
                try {
                    ret.add(Stock.getPart(s1, s2));
                } catch (InexistantPartException e) {
                    System.out
                            .println("Erreur dans les pièces pour le vélo (cette erreur ne doit pas etre levée)");
                }
            }
        }
        Bike b = null;
        try {
            b = new Bike(ret);
        } catch (Exception e) {
            System.out
                    .println(("Erreur lors de la création du vélo (cette erreur ne doit pas etre levée)"));
        }
        System.out.println(b);
        Stock.add(b);
    }

    private void stockPrice() {
        System.out.println("Le montant total de votre stock est : "
                + stockm.stock.Stock.totalPrice() + "€");
    }

    private boolean isCart(String nom) {
        boolean b = false;
        for (Cart c : Stock.getCarts()) {
            if (c.getNom().equals(nom)) {
                b = true;
                break;
            }
        }
        return b;
    }

    private void cart(ArrayList<String> list) {
        if (list.size() == 1) {
            System.out.println("Liste des paniers existant");
            if (Stock.getCarts().size() == 0) {
                System.out.println("Il n'y a aucun panier dans la liste");
            } else {
                for (Cart c : Stock.getCarts()) {
                    System.out.println(c.getNom());
                }
            }
        } else if (list.size() == 2 && list.get(1).equals("-c")) {
            if (current == null) {
                System.out.println("Pas de panier courant");
            } else {
                System.out.println("[Panier courant]\n");
                System.out.println(current);
            }
        } else if (list.size() >= 2 && list.get(1).charAt(0) == '-') {
            System.out
                    .println("Le nom d'un panier ne doit pas commencer par un '-'");
        } else {
            if (!isCart(list.get(1))) {
                Cart c = new Cart(list.get(1));
                current = c;
                Stock.add(c);
                System.out
                        .println("Le panier "
                                + list.get(1)
                                + " a été créé et ajouté a la liste, c'est votre panier courant");
            } else {
                try {
                    current = Stock.getCart(list.get(1));
                } catch (InexistantElementException e) {
                    System.out.println("Erreur de programmation");
                }
            }
        }
    }

    private void buy() {
        try {
            if (current != null) {
                current.sell();
                current = null;
            } else
                System.out.println("Pas de panier courant");
        } catch (Throwable e) {
            System.out
                    .println("Erreur lors de l'achat du panier. Réessayez. Si le problème persiste redémarrez le programme.");
        }
    }

    private void delete() {
        try {
            if (current != null) {
                current.delete();
                current = null;
            } else
                System.out.println("Pas de panier courant");
        } catch (Throwable e) {
            System.out
                    .println("Erreur lors de la suppression du panier. Réessayez. Si le problème persiste redémarrez le programme.");
        }
    }

    private void sales() {
        stockm.stock.Stock.listSales();
    }

    /**
     * Add a new entry.
     */
    private void add(ArrayList<String> list) {
        System.out.println(list);
        String s1, s2 = "";
        boolean n = false;
        int nb = 0;
        Part p;
        Bike b;

        for (int i = 1; i < list.size(); i = i + 2) {
            if (i < list.size() - 2 && list.get(i + 2).charAt(0) == '#') {
                n = true;
                nb = Integer.parseInt(list.get(i + 2).substring(1));
            }
            s1 = cw.optionsTyp.get((list.get(i)));
            s2 = list.get(i + 1);
            try {
                if (s1.equals("bike")) {
                    try {
                        b = stockm.stock.Stock.getBikes().get(
                                Integer.parseInt(s2) - 1);
                        current.add(b);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Vélo inexistant");
                    }
                } else if (n) {
                    p = stockm.stock.Stock.getPart(s1, s2);
                    current.add(p, nb);
                    i++;
                } else {
                    p = stockm.stock.Stock.getPart(s1, s2);
                    current.add(p);
                }
            } catch (InexistantPartException e) {
                System.out.println("Pièce inexistante");
            } catch (BadArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void remove(ArrayList<String> list) {
        System.out.println(list);
        String s1, s2 = "";
        boolean n = false;
        int nb = 0;
        Part p;
        Bike b;

        for (int i = 1; i < list.size(); i = i + 2) {
            if (i < list.size() - 2 && list.get(i + 2).charAt(0) == '#') {
                n = true;
                nb = Integer.parseInt(list.get(i + 2).substring(1));
            }
            s1 = cw.optionsTyp.get((list.get(i)));
            s2 = list.get(i + 1);
            try {
                if (s1.equals("bike")) {
                    int j = 0;
                    b = null;
                    for (Iterator<Bike> it = current.getBikes().keySet()
                            .iterator(); it.hasNext();) {
                        Bike tmp = it.next();
                        if (j == Integer.parseInt(s2) - 1) {
                            b = tmp;
                            break;
                        }
                        j++;
                    }
                    if (b == null)
                        throw new InexistantElementException("Interface",
                                "remove(list)");
                    current.remove(b);
                } else if (n) {
                    p = stockm.stock.Stock.getPart(s1, s2);
                    current.remove(p, nb);
                    i++;
                } else {
                    p = stockm.stock.Stock.getPart(s1, s2);
                    current.remove(p);
                }
            } catch (InexistantElementException e) {
                System.out.println("Vélo inexistant");
            } catch (InexistantPartException e) {
                System.out.println("Pièce inexistante");
            } catch (BadArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * List the available commands.
     */
    private void help() {
        String chaine = "";
        String fichier = "help.txt";

        // lecture du fichier texte
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                chaine += ligne + "\n";
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * List the address book's contents.à
     */
    private void list(ArrayList<String> list) {
        if (list.size() == 1) {
            stockm.stock.Stock.list();
        } else {
            for (int i = 1; i < list.size(); i++) {
                for (Part p : stockm.stock.Stock.getParts(cw.optionsTyp
                        .get(list.get(i)))) {
                    System.out.println(p);
                }
            }
        }
    }

    private void sellPrice() {
        if (current != null)
            System.out.println(current.getPrice() + "€");
        else
            System.out.println("Pas de panier courant");
    }
}
