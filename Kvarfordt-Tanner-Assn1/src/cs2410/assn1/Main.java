package cs2410.assn1;

/**Prints out the first and last name of the author, and a message
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class Main {

    /**
     * public String for storing first name of the author
     */
    public static String fName = "Tanner";
    /**
     *public String for storing the last name of the author
     */
    public static String lName = "Kvarfordt";

    /**
     *
     * @param args command line arguments for the program
     */
    public static void main(String[] args) {
        PrintName();
    }

    /**
     * prints first and last name of the author along with a message
     */
    public static void PrintName(){
        System.out.println(fName + ' ' + lName +"\nWOOOkokokkokoO first Java Code!");
    }
}
