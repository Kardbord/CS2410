package cs2410.assn2;

/**
 * @author Tanner Kvarfordt
 * @version 1.0
 */

/**
 * Prints numbers 1 to TOP_END (defined in Fizzy) to the screen
 * If a number is a multiple of 3, "Fizz" is printed instead of the number
 * If a number is a multiple of 5, "Buzz" is printed instead of the number
 */
public class Fizzy {

    /**
     * Top end for numbers to print to the screen
     */
    private static final int TOP_END = 100;

    /**
     * counter for iterating to 100
     */
    private static int counter = 1;


    /**
     * checks if a value is a multiple of three
     *
     * @param val value to be checked as to if it is a multiple of three
     * @return true if val is a multiple of three
     */
    private static boolean isFizz(int val){
        if (val % 3 == 0) return true;
        return false;
    }

    /**
     * checks if a value is a multiple of five
     *
     * @param val value to be checked as to if it is a multiple of five
     * @return true if val is a multiple of five
     */
    private static boolean isBuzz(int val){
        if (val % 5 == 0) return true;
        return false;
    }

    /**
     *
     * @param args Command line arguments for the program
     */
    public static void main(String args[]){
        while (counter <= TOP_END){
            if (isFizz(counter) && isBuzz(counter)){
                System.out.println("FizzBuzz");
            }
            else if (isBuzz(counter)){
                System.out.println("Buzz");
            }
            else if (isFizz(counter)){
                System.out.println("Fizz");
            }
            else {
                System.out.println(counter);
            }
            ++counter;
        }
    }
}
