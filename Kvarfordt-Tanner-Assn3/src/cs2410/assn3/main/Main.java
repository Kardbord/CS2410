package cs2410.assn3.main;

import cs2410.assn3.command.CommandDirectory;

/**
 * Kvarfordt-Tanner-Assn3
 * Created on 9/12/2016
 *
 * Creates an instance of the CommandDirectory class.
 * Also holds the name of the input file used in both
 * CommandDirectory and GUIDirectory classes.
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class Main {
    /**
     * Name of the directory file we will be using
     */
    public static final String fileName = "data/cs2410-directory.data";

    /**
     * @param args command line arguments for the program
     */
    public static void main(String args[]){
        new CommandDirectory();
        //run the GUI from GUIDirectory.java
    }
}
