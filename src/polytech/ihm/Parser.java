package stockm.ihm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class that reads input lines from the user. Input is filtered via
 * getCommand for valid commands.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Parser {
    // Hold all valid command words.
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Read the next command from the user. The returned command will be valid.
     * 
     * @return A valid command.
     */
    public ArrayList<String> getCommand() {
    	ArrayList<String> chaine = null;
    	String commande="";
        while (chaine == null) {
            // Print a prompt.
            System.out.print("> ");
            commande =reader.nextLine();
            chaine = cutString(commande);
            if (!commands.isCommand(chaine)) {
            	chaine =null;
                System.out.println("Unrecognized command: or bad arguments " + commande);
                System.out.print("Valid commands are: ");
                commands.showAll();
            }
        }

        return chaine;
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands() {
        commands.showAll();
    }

    /**
     * @return A line of text from the user.
     */
    public String readLine() {
        return reader.nextLine();
    }
    
    public ArrayList<String> cutString(String s){
    	ArrayList<String> ret = new ArrayList<String>();
    	while(s.indexOf(' ')!=-1){
    		ret.add(s.substring(s.lastIndexOf(' ')+1, s.length()).toLowerCase());
    		s=s.substring(0,s.lastIndexOf(' '));
    	}
    	ret.add(s);
    	Collections.reverse(ret);
    	return ret;
    }
}
