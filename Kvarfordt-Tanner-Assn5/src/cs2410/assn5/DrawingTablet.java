package cs2410.assn5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Kvarfordt-Tanner-Assn5
 * Created on 10/15/2016
 *
 * This class creates a GUI in which a user may draw and delete ellipses
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class DrawingTablet extends Application {

    /**
     * Handler for all Ellipses created in drawPane.
     * Handles the deletion of ellipses when in erase mode
     */
    private static EventHandler<MouseEvent> eraseHandler;

    /**
     * Handler for the draw and erase buttons.
     * Determines which button is pressed and performs appropriate tasks
     */
    private static EventHandler<ActionEvent> buttonHandler;

    /**
     * Button for display in the toolbar Pane
     * Assigned to buttonHandler when pressed
     */
    private static Button draw;

    /**
     * Button for display in the toolbar Pane
     * Assigned to buttonHandler when pressed
     */
    private static Button erase;

    /**
     * Pane containing the allowed drawing area
     */
    private static Pane drawPane;

    /**
     * True if in erase mode, false if in draw mode
     */
    private static boolean eraseIsSelected;

    /**
     * Contains the X coordinates of the mouse when a drag is started
     */
    private static double startDragX;

    /**
     * Contains the Y coordinates of the mouse when a drag is started
     */
    private static double startDragY;

    /**
     * Contains the X coordinates of the mouse as a drag is performed
     */
    private static double endDragX;

    /**
     * Contains the Y coordinates of the mouse as a drag is performed
     */
    private static double endDragY;

    /**
     * a temporary ellipse used to add ellipses to drawPane
     */
    private static Ellipse tmpEll;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //initialize buttons and set location
        draw = new Button("Draw");
        erase = new Button("Erase");
        draw.setLayoutX(194);
        erase.setLayoutX(264);
        draw.setLayoutY(50);
        erase.setLayoutY(50);

        //initialize/create necessary panes
        Pane mainPane = new Pane();

        drawPane = new Pane();
        drawPane.setPrefSize(500, 500);
        drawPane.setLayoutY(100);
        drawPane.setLayoutX(0);

        Pane toolbar = new Pane();
        toolbar.setPrefSize(500, 100);

        toolbar.getChildren().addAll(draw, erase);
        mainPane.getChildren().addAll(drawPane, toolbar);

        //set proper clip for drawPane
        Rectangle clip = new Rectangle(500, 500);
        drawPane.setClip(clip);

        //add panes to scene
        Scene scene1 = new Scene(mainPane, 500, 600);

        //initialize necessary handlers
        initHandlers();
        eraseIsSelected = false;
        draw.setOnAction(buttonHandler);
        erase.setOnAction(buttonHandler);
        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!eraseIsSelected) {
                    startDragX = event.getX();
                    startDragY = event.getY();
                    tmpEll = new Ellipse();
                    tmpEll.setStroke(Color.PEACHPUFF);
                    tmpEll.setStrokeWidth(5);
                    tmpEll.setFill(Color.MEDIUMSPRINGGREEN);
                    drawPane.getChildren().add(tmpEll);
                }
            }
        });
        drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!eraseIsSelected) {
                    endDragX = event.getX();
                    endDragY = event.getY();

                    tmpEll.setRadiusX(Math.abs(endDragX - startDragX) / 2);
                    tmpEll.setRadiusY(Math.abs(endDragY - startDragY) / 2);
                    tmpEll.setCenterX(startDragX + (endDragX - startDragX) / 2);
                    tmpEll.setCenterY(startDragY + (endDragY - startDragY) / 2);
                    tmpEll.setOnMousePressed(eraseHandler);
                }
            }
        });

        //setScene, setResizeable = false, show the stage
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing Tablet");
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    /**
     * Initializes all handlers for DrawingTablet
     */
    private static void initHandlers() {
        buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == erase) {
                    eraseIsSelected = true;
                } else if (event.getSource() == draw) {
                    eraseIsSelected = false;
                }
            }
        };

        eraseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (eraseIsSelected) {
                    Ellipse tmpEll = (Ellipse) event.getSource();
                    drawPane.getChildren().remove(tmpEll);
                }
            }
        };
    }

}
