package stockm.ihm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the program.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class CommandWords {
    // a constant array that holds all valid command words
    private enum Command {
        help("help"),
        list("list"),
        stockprice("stockprice"),
        sales("sales"),
        sellprice("sellprice"),
        cart("cart"),
        cartAdd("cart+"),
        cartDel("cart-"),
        buy("buy"),
        delete("delete"),
        save("save"),
        quit("quit");
        
        private String text;

        Command(String text) {
            this.text = text;
        }
        
        public String toString() {
            return text;
        }
    }
    
    public static final int NBCOMMANDS = 12;
    public static HashMap<String, String> optionsTyp;
    public static HashMap<String, String> bikeTyp = new HashMap<String, String>();

    /**
     * Constructor for CommandWords
     */
    public CommandWords() {
        optionsTyp = getOption(stockm.stock.Stock.getTypes());
        optionsTyp.put("-b", "bike");
        bikeTyp = getOption(stockm.types.Bike.requiredParts);
    }

    // Key : option
    // Value : type
    public HashMap<String, String> getOption(ArrayList<String> list) {
        HashMap<String, String> ret = new HashMap<String, String>();
        String s = "";
        for (String l : list) {
            s = "-" + l.charAt(0) + l.charAt(1);
            int i = 2;
            while (ret.containsValue(s)) {
                s += l.charAt(i);
                i++;
            }
            s = s.toLowerCase();
            ret.put(s, l);
        }
        return ret;
    }

    public String getType(String s) {
        return optionsTyp.get(s);
    }

    /**
     * @return the validcommands
     */
    public static String[] getValidcommands() {
        String[] validCommands = new String[NBCOMMANDS];
        
        int i = 0;
        for (Command c : Command.values()) {
            validCommands[i] = c.toString();
            i++;
        }
        
        return validCommands;
    }

    public boolean argEmpty(ArrayList<String> list) {
        if (list.contains("") || list.contains(" ")) {
            return true;
        } else
            return false;
    }

    /**
     * @return the bikeTyp
     */
    public static HashMap<String, String> getBikeTyp() {
        return bikeTyp;
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param aString
     *            The string to be checked.
     * @return true if it is valid, false if it isn't.
     */
    public boolean isCommand(ArrayList<String> list) {
        if (list != null && !argEmpty(list)) {
            for (int i = 0; i < getValidcommands().length; i++) {
                if (getValidcommands()[i].equals(list.get(0))) {
                    if (list.get(0).equals("bike")) {
                        return isBike(list);
                    }
                    if (list.get(0).equals("list")) {
                        return isType(list);
                    }
                    if (list.get(0).equals("cart+")
                            || list.get(0).equals("cart-")) {
                        return isTypeToMove(list);
                    }
                    if (list.get(0).equals("cart") && list.size() < 1
                            && list.size() > 2) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public boolean isBike(ArrayList<String> list) {
        Boolean b = true;
        if (list.size() % 2 == 0 || (list.size() - 1) / 2 != bikeTyp.size()) {
            b = false;
        } else {
            for (int i = 1; i < list.size() - 1; i = i + 2) {
                if (!bikeTyp.containsKey(list.get(i))) {
                    b = false;
                }
            }
        }
        return b;
    }

    public boolean isType(ArrayList<String> list) {
        boolean b = true;
        for (int i = 1; i < list.size(); i++) {
            if (!optionsTyp.containsKey(list.get(i))) {
                b = false;
            }
        }
        return b;
    }

    public boolean isTypeToMove(ArrayList<String> list) {
        for (int i = 1; i < list.size() - 1; i = i + 2) {
            if (!optionsTyp.containsKey(list.get(i)))
                return false;
            else if (i < list.size() - 3 && list.get(i + 2).charAt(0) == '#')
                i++;
            else if (i == list.size() - 3 && list.get(i + 2).charAt(0) != '#')
                return false;
        }
        return true;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() {
        for (String command : getValidcommands()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
