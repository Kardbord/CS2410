package view;

import control.Control;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * The main portion of the view; creates and displays the GUI
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */

public class Start extends Application {

    /**
     * Button allowing a user to save a newly created person
     */
    private Button saveBtn = new Button("Save");

    /**
     * Button allowing a user to clear all TextFields when creating a new worker
     */
    private Button cancelBtn = new Button("Cancel");

    /**
     * Pane containing saveBtn and cancelBtn
     */
    private HBox savePane = new HBox(6);

    /**
     * ToolBar (Pane) to provide the user with a menu
     */
    private ToolBar toolPane = new ToolBar();

    /**
     * The main Pane - displayed at all times while the program is running
     */
    private BorderPane mainPane = new BorderPane();

    /**
     * Pane for displaying any information to the user
     */
    private Pane displayPane = new Pane();

    /**
     * AddPane for allowing the user to add a new person
     */
    private AddPane addPane;

    /**
     * Control object for creating and storing new workers, and getting any relevant information about them
     */
    private Control control = new Control();

    /**
     * Start method - performs the displaying of all Panes
     * @param primaryStage primary stage for the program
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        control.makeNewContractWorker("Bob", "5 / 5 = 1", "I like stars", "50", "5", "100");
        control.makeNewHourlyWorker("Steve", "5 * 5 = 25", "I like snails", "20", "40", "110");
        control.makeNewHobbit("Frodo", "5 + 5 = 10", "I like dirt", "12");

        savePane.getChildren().addAll(saveBtn, cancelBtn);
        savePane.setAlignment(Pos.BOTTOM_RIGHT);
        savePane.setPadding(new Insets(20));

        toolPane.setAlignment(Pos.CENTER);

        mainPane.setTop(toolPane);

        setButtonActions();

        //set scene and show stage
        Scene scene1 = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Polyabstraheritance");
        primaryStage.show();
    }

    /**
     * Creates listeners for all buttons in the program
     */
    private void setButtonActions() {
        toolPane.setHobbitBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toolPane.clearComboBoxText();
                mainPane.setBottom(savePane);
                addPane = new AddPane("Name:", "Math:", "Say:", "Carrots:");
                for (TextField s : addPane.getAllFields()){
                    s.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            toolPane.setHobbitBtnSelected(true);
                        }
                    });
                }
                mainPane.setCenter(addPane);
            }
        });
        toolPane.setContractBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toolPane.clearComboBoxText();
                mainPane.setBottom(savePane);
                addPane = new AddPane("Name:", "Math:", "Say:", "Pay Per Contract:", "Contracts:", "IQ:");
                mainPane.setCenter(addPane);
            }
        });
        toolPane.setHourlyBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toolPane.clearComboBoxText();
                mainPane.setBottom(savePane);
                addPane = new AddPane("Name:", "Math:", "Say:", "Wage:", "Hours:", "IQ:");
                mainPane.setCenter(addPane);
            }
        });

        cancelBtn.setOnAction(event -> addPane.getAllFields().forEach(TextInputControl::clear));

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> inputs = new ArrayList<>(6);
                for (TextField t : addPane.getAllFields()) {
                    inputs.add(t.getText());
                }

                /*
                 * The type of person we are creating can be determined by the 4th TextField's prompt text in
                 * addPane at this time. This is a workaround to the bug where, after clicking on the ComboBox
                 * and then to an add button, the add button will trigger the proper addPane to show, but will not
                 * actually select the button, causing user input to be ignored. Other workaround attempts, such as
                 * setting the button to be selected in its EventHandler<ActionEvent>, failed. So this will do.
                 */
                String promptText = addPane.getAllFields().get(3).getPromptText();

                if (promptText == "Carrots:") {
                    control.makeNewHobbit(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3));
                } else if (promptText == "Pay Per Contract:") {
                    control.makeNewContractWorker
                            (inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5));
                } else if (promptText == "Wage:"){
                    control.makeNewHourlyWorker
                            (inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5));
                }
                addPane.getAllFields().forEach(TextInputControl::clear);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You added " + inputs.get(0));
                alert.setTitle("Add Confirmed");
                alert.showAndWait();
            }
        });
        toolPane.setComboBoxAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toolPane.deselectToggles();
                mainPane.setBottom(null);
                displayPane.getChildren().clear();
                Label label = new Label();
                label.setAlignment(Pos.CENTER);

                if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(0))) {
                    label.setText(control.getMaths());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(1))) {
                    label.setText(control.getIncomes());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(2))) {
                    label.setText(control.getHours());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(3))) {
                    label.setText(control.getIQs());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(4))) {
                    label.setText(control.getSayList());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(5))) {
                    label.setText(control.getCarrots());
                } else if (Objects.equals(toolPane.getComboSelection(), toolPane.getComboItems().get(6))) {
                    label.setText(control.getContracts());
                }
                displayPane.getChildren().add(label);
                TilePane t = new TilePane(displayPane); //using this to set the alignment of displayPane
                t.setAlignment(Pos.CENTER);
                mainPane.setCenter(t);
            }
        });
    }
}
