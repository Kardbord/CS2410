package cs2410.assn3.command;

import cs2410.assn3.main.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Vector;

/**
 * Kvarfordt-Tanner-Assn3
 * Created on 9/12/2016
 *
 * Creates a menu in the command line for performing
 * various tasks in a directory of students.
 * Functionality includes:
 *      - viewing the directory
 *      - adding entries to the directory
 *      - calculating and displaying the average age of all
 *        students in the directory
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class CommandDirectory {

    public CommandDirectory() {

        /*
          Scanner object for receiving user input
         */
        Scanner cin = new Scanner(System.in);

        /*
          String object for use in a switch statement below
         */
        int sw;

        while (true) {

            System.out.println("\nPlease enter the number of your selection.\n");

            System.out.println(
                    "1. List directory contents\n"
                            + "2. Add student to directory\n"
                            + "3. Display average age of students\n"
                            + "4. Quit program\n");

            sw = cin.nextInt();

            switch (sw) {
                case 1:

                    Scanner fileInput = null;

                    try {
                        fileInput = new Scanner(new FileReader(Main.fileName));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    System.out.print("\n");

                    String colHead1 = "First Name";
                    String colHead2 = "Last Name";
                    String colHead3 = "Age";
                    String colHead4 = "Phone #";
                    System.out.printf("%-16s %-15s %s %14s %n", colHead1, colHead2, colHead3, colHead4);

                    assert fileInput != null;
                    while (fileInput.hasNext()) {

                        System.out.printf("%-16s %-15s %3s %16s %n", fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next());

                    }

                    fileInput.close();
                    break;

                case 2:
                    String fName;
                    String lName;
                    String age;
                    String pNum; //phone number

                    BufferedWriter fileOutput = null;

                    try {
                        FileWriter tempWriter = new FileWriter(Main.fileName, true);
                        fileOutput = new BufferedWriter(tempWriter);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Enter the student's first name:");
                    fName = cin.next();

                    System.out.println("Enter the student's last name");
                    lName = cin.next();

                    System.out.println("Enter the student's age:");
                    age = cin.next();

                    System.out.println("Enter the student's phone number (XXX-XXX-XXXX):");
                    pNum = cin.next();

                    try {
                        assert fileOutput != null;
                        fileOutput.write("\n");
                        fileOutput.write(fName);
                        fileOutput.write(" ");
                        fileOutput.write(lName);
                        fileOutput.write(" ");
                        fileOutput.write(age);
                        fileOutput.write(" ");
                        fileOutput.write(pNum);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        fileOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    Vector<Integer> ages = new Vector<>();

                    Scanner fin = null;
                    try {
                        fin = new Scanner(new FileReader(Main.fileName));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    double totalAges = 0;
                    double averageAge;
                    assert fin != null;
                    for (int count = 0; fin.hasNext(); count++) {
                        fin.next();
                        fin.next();
                        ages.add(count, fin.nextInt());
                        fin.next();
                        totalAges += ages.elementAt(count);
                    }

                    averageAge = totalAges / ages.size();

                    NumberFormat decFormat = new DecimalFormat("#0.00");
                    System.out.println("\nAverage Student Age: " + decFormat.format(averageAge));

                    break;

                case 4:
                    return;
                    /*falls through but not really*/
                default:
                    System.out.println("Please enter a valid selection number.\n");
            }

        }

    }
}

