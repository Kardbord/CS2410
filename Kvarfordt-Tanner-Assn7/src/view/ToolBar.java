package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * Tool bar with buttons to create new workers, and a ComboBox to select information to view
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class ToolBar extends HBox {

    /**
     * ComboBox for the toolbar
     */
    private ComboBox<String> dispOptions =
            new ComboBox<>(FXCollections.observableArrayList("Math", "Income", "Hours", "IQ", "Say", "Carrots", "Contracts"));

    /**
     * ToggleGroup for Hourly, Contract, and Hobbit buttons
     */
    ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Button to add a new HourlyWorker
     */
    private ToggleButton hourlyBtn = new ToggleButton("Hourly");

    /**
     * Button to add a new ContractWorker
     */
    private ToggleButton contractBtn = new ToggleButton("Contract");

    /**
     * Button to add a new Hobbit
     */
    private ToggleButton hobbitBtn = new ToggleButton("Hobbit");

    /**
     * ToolBar constructor - adds all buttons and to the HBox and sets spacing and padding
     */
    public ToolBar() {

        this.getChildren().addAll(dispOptions, hourlyBtn, contractBtn, hobbitBtn);

        toggleGroup.getToggles().addAll(hourlyBtn, contractBtn, hobbitBtn);
        hourlyBtn.setSelected(false);
        contractBtn.setSelected(false);
        hobbitBtn.setSelected(false);

        this.setPadding(new Insets(10, 5, 5, 5));
        this.setSpacing(6);
    }

    /**
     * Method to deselect all toggles
     */
    public void deselectToggles() {
        for (Toggle b : toggleGroup.getToggles()) {
            if (b.isSelected()) b.setSelected(false);
        }
    }

    /*public boolean hourlyBtnIsSelected() {return hourlyBtn.isSelected();}

    public boolean contractBtnIsSelected() {
        return contractBtn.isSelected();
    }

    public boolean hobbitBtnIsSelected() {
        return hobbitBtn.isSelected();
    }
    */

    /**
     * Method to set whether or not the Hobbit button is selected
     * @param b true or false; button is set as selected if true, not selected if false
     */
    public void setHobbitBtnSelected(boolean b){
        hobbitBtn.setSelected(b);
    }

    /**
     * Creates a listener for the hourlyBtn
     * @param e the listener
     */
    public void setHourlyBtnAction(EventHandler<ActionEvent> e) {
        hourlyBtn.setOnAction(e);
    }

    /**
     * Creates a listener for the ContractBtn
     * @param e the listener
     */
    public void setContractBtnAction(EventHandler<ActionEvent> e) {
        contractBtn.setOnAction(e);
    }

    /**
     * Creates a listener for HobbitBtn
     * @param e the listener
     */
    public void setHobbitBtnAction(EventHandler<ActionEvent> e) {
        hobbitBtn.setOnAction(e);
    }

    /**
     * Deselects all options in the ComboBox
     */
    public void clearComboBoxText() {
        dispOptions.getSelectionModel().clearSelection();
    }

    /**
     * Creates a listener for the ComboBox
     * @param e the listener
     */
    public void setComboBoxAction(EventHandler<ActionEvent> e) {
        dispOptions.setOnAction(e);
    }

    /**
     * @return the text options in the ComboBox
     */
    public ObservableList<String> getComboItems() {
        return dispOptions.getItems();
    }

    /**
     * @return the currently selected ComboBox item
     */
    public String getComboSelection() {
        return dispOptions.getSelectionModel().getSelectedItem();
    }

}