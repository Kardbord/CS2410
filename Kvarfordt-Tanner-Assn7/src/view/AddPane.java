package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * AddPane is a pane containing either 4 or 6 TextFields lined up vertically.
 * TextField prompts are set in the constructor
 * Extends VBox
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */

public class AddPane extends VBox {

    /**
     * A generic TextField object used in the two constructors to create necessary TextFields
     */
    private TextField field;

    /**
     * A container for every TextField created for the AddPane
     */
    private ArrayList<TextField> allFields = new ArrayList<>();

    /**
     * AddPane constructor. Takes six arguments, creates 6 vertically lined up TextField with prompt texts given
     *
     * @param a prompt for first TextField
     * @param b prompt for second TextField
     * @param c prompt for third TextField
     * @param d prompt for fourth TextField
     * @param e prompt for fifth TextField
     * @param f prompt for sixth TextField
     */
    public AddPane(String a, String b, String c, String d, String e, String f) {
        for (int ct = 0; ct < 6; ++ct) {
            field = new TextField();
            allFields.add(field);
            field.setMaxWidth(400);
            if (ct == 0) {
                field.setPromptText(a);
            }
            if (ct == 1) {
                field.setPromptText(b);
            }
            if (ct == 2) {
                field.setPromptText(c);
            }
            if (ct == 3) {
                field.setPromptText(d);
            }
            if (ct == 4) {
                field.setPromptText(e);
            }
            if (ct == 5) {
                field.setPromptText(f);
            }
            this.setPadding(new Insets(80, 100, 0, 100));
            this.setAlignment(Pos.TOP_CENTER);
            this.setSpacing(8);
            this.getChildren().add(field);
        }

    }

    /**
     * AddPane constructor. Takes four arguments, creates 4 vertically lined up TextField with prompt texts given
     *
     * @param a prompt for first TextField
     * @param b prompt for second TextField
     * @param c prompt for third TextField
     * @param d prompt for fourth TextField
     */
    public AddPane(String a, String b, String c, String d) {
        for (int ct = 0; ct < 4; ++ct) {
            field = new TextField();
            allFields.add(field);
            field.setMaxWidth(400);
            if (ct == 0) {
                field.setPromptText(a);
            }
            if (ct == 1) {
                field.setPromptText(b);
            }
            if (ct == 2) {
                field.setPromptText(c);
            }
            if (ct == 3) {
                field.setPromptText(d);
            }
            this.setPadding(new Insets(100, 100, 0, 100));
            this.setAlignment(Pos.TOP_CENTER);
            this.setSpacing(8);
            this.getChildren().add(field);
        }
    }

    /**
     * @return container with every TextField for the AddPane
     */
    public ArrayList<TextField> getAllFields() {
        return allFields;
    }
}
