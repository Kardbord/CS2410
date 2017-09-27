package cs2410.assn3.gui;

import cs2410.assn3.main.Main;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.Scanner;
import java.util.Vector;

/**
 * Kvarfordt-Tanner-Assn3
 * Created on 9/19/2016
 *
 * Creates a GUI menu for performing various tasks with a
 * non-alphabetized directory of students.
 * Functionality includes:
 *      - viewing the directory
 *      - adding entries to the directory
 *      - calculating and displaying the average age of all
 *        students in the directory
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class GUIDirectory extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextInputDialog fName = new TextInputDialog();
        fName.setTitle("Add Person");
        fName.setGraphic(null);
        fName.setHeaderText(null);
        fName.setContentText("Enter the student's first name: ");

        TextInputDialog lName = new TextInputDialog();
        lName.setTitle("Add Person");
        lName.setGraphic(null);
        lName.setHeaderText(null);
        lName.setContentText("Enter the student's last name: ");

        TextInputDialog age = new TextInputDialog();
        age.setTitle("Add Person");
        age.setHeaderText(null);
        age.setContentText("Enter the student's age: ");

        TextInputDialog pNum = new TextInputDialog();
        pNum.setTitle("Add Person");
        pNum.setGraphic(null);
        pNum.setHeaderText(null);
        pNum.setContentText("Enter the student's phone number (XXX-XXX-XXXX): ");

        Alert displayDirectory = new Alert(Alert.AlertType.INFORMATION);
        displayDirectory.setTitle("Directory Listing");
        displayDirectory.setHeaderText(null);
        displayDirectory.setGraphic(null);
        displayDirectory.getDialogPane().setPrefSize(600, 400);
        displayDirectory.getDialogPane().getStylesheets().add("resources/paneFormat.css");

        Alert avgAge = new Alert(Alert.AlertType.INFORMATION);
        avgAge.setTitle("Average Student Age");
        avgAge.setGraphic(null);
        avgAge.setHeaderText(null);

        Alert invalidSelection = new Alert(Alert.AlertType.ERROR);
        invalidSelection.setTitle("Invalid Input!");
        invalidSelection.setHeaderText(null);
        invalidSelection.setContentText("Please enter a valid selection number.");

        TextInputDialog input = new TextInputDialog();
        input.setTitle("Main Menu");
        input.setHeaderText(null);
        input.setGraphic(null);
        input.setContentText("Enter the Number of your selection:\n"
                + "1. List directory contents\n"
                + "2. Add student to directory\n"
                + "3. Display average age of students\n"
                + "4. Quit program\n");

            Optional<String> result;

            while (true) {

                result = input.showAndWait();

                switch (Integer.parseInt(result.get())) {
                    case 1:
                        Scanner fileInput = null;
                        try {
                            fileInput = new Scanner(new FileReader(Main.fileName));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                        String colHead1 = "First Name";
                        String colHead2 = "Last Name";
                        String colHead3 = "Age";
                        String colHead4 = "Phone #";

                        String fullText = String.format("%-16s %-15s %s %14s %n", colHead1, colHead2, colHead3, colHead4);

                        assert fileInput != null;
                        while (fileInput.hasNext()) {
                            fullText = fullText.concat(String.format("%-16s %-15s %3s %16s", fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next()));
                            fullText = fullText.concat("\n");
                        }

                        displayDirectory.setContentText(fullText);
                        displayDirectory.showAndWait();
                        fileInput.close();
                        break;

                    case 2:
                        String newFName;
                        String newLName;
                        String newAge;
                        String newPNum;

                        Optional<String> temp;

                        temp = fName.showAndWait();
                        newFName = temp.get();

                        temp = lName.showAndWait();
                        newLName = temp.get();

                        temp = age.showAndWait();
                        newAge = temp.get();

                        temp = pNum.showAndWait();
                        newPNum = temp.get();

                        BufferedWriter fileOutput = null;

                        try {
                            FileWriter tempWriter = new FileWriter(Main.fileName, true);
                            fileOutput = new BufferedWriter(tempWriter);

                            fileOutput.write("\n");
                            fileOutput.write(newFName);
                            fileOutput.write(" ");
                            fileOutput.write(newLName);
                            fileOutput.write(" ");
                            fileOutput.write(newAge);
                            fileOutput.write(" ");
                            fileOutput.write(newPNum);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        assert fileOutput != null;
                        fileOutput.close();
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

                        avgAge.setContentText("The average student age is " + decFormat.format(averageAge));

                        avgAge.showAndWait();

                        fin.close();
                        break;

                    case 4:
                        System.exit(0);
                    default:
                        invalidSelection.showAndWait();
                }
                input.getEditor().clear();
                fName.getEditor().clear();
                lName.getEditor().clear();
                age.getEditor().clear();
                pNum.getEditor().clear();
            }
    }
}
