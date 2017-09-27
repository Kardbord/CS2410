package cs2410.assn4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Kvarfordt-Tanner-Assn4
 * Created on 9/30/2016
 *
 * Add a short description of the class here
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class ImageViewerView extends Application {
    /**
     * EventHandler to communicate with controller class when buttons are pressed
     */
    private EventHandler<ActionEvent> handler1;

    /**
     * "next" button for going to the next image
     */
    private static Button next;

    /**
     * "previous" button for returning to previous image
     */
    private static Button prev;

    /**
     * "del" button for deleting the current image
     */
    private static Button del;

    /**
     * "add" button for inserting a new image after the current image
     */
    private static Button add;

    /**
     * an ImageView node for primaryStage
     */
    private static ImageView imageNode;

    /**
     * TextInputDialog for adding a new image
     */
    static TextInputDialog getNewUrl;

    /**
     * url for image to display when no images are found in the input file
     */
    static String noImage = "http://www.bnimastermind.com/wp-content/themes/Saroja/images/no-image.jpg";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Image Viewer");
        primaryStage.setResizable(false);

        //initialize TextInputDialog for use with "add" button
        getNewUrl = new TextInputDialog();
        getNewUrl.setContentText("Type the full url of the image you would like to add:");
        getNewUrl.setHeaderText(null);
        getNewUrl.setTitle("Add Image");
        getNewUrl.setGraphic(null);

        //call default constructor of controller class
        new ImageViewerCtrl();

        //initialize handlers
        initHandlers();

        //a pane for a Scene
        Pane pane1 = new Pane();

        //a scene for primaryStage
        Scene scene1 = new Scene(pane1, 500, 600);

        //initialize buttons
        next = new Button("Next");
        prev = new Button("Previous");
        del = new Button("Delete");
        add = new Button("Add");

        //set handlers
        next.setOnAction(handler1);
        prev.setOnAction(handler1);
        add.setOnAction(handler1);
        del.setOnAction(handler1);

        //position nodes in the pane
        next.setLayoutX(325);
        next.setLayoutY(550);
        prev.setLayoutX(112);
        prev.setLayoutY(550);
        add.setLayoutX(200);
        add.setLayoutY(550);
        del.setLayoutX(250);
        del.setLayoutY(550);

        //display first image if there is one
        imageNode = new ImageView();
        imageNode = new ImageView(ImageViewerCtrl.nextImage());
        imageNode.setLayoutX(10);
        imageNode.setLayoutY(90);
        imageNode.setPreserveRatio(true);
        imageNode.setSmooth(true);
        imageNode.setFitWidth(490);
        //imageNode.setFitHeight(600);

        //add all needed Nodes to the pane
        pane1.getChildren().addAll(next, prev, del, add, imageNode);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * Override of Application's stop function
     *
     * Writes over the original images.data file with
     * the new urls
     */
    public void stop(){
        ImageViewerCtrl.quit();
    }

    /**
     * function to initialize all handlers for the class
     */
    private void initHandlers() {
        handler1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == next) {
                    imageNode.setImage(ImageViewerCtrl.nextImage());
                } else if (event.getSource() == prev) {
                    imageNode.setImage(ImageViewerCtrl.prevImage());
                } else if (event.getSource() == del) {
                    imageNode.setImage(ImageViewerCtrl.delImage());
                } else if (event.getSource() == add) {
                    imageNode.setImage(ImageViewerCtrl.addImage());
                }
            }
        };
    }

    /**
     * disarms buttons in the
     * event that no images are available
     */
    static void disableButtons() {
        next.setDisable(true);
        prev.setDisable(true);
        del.setDisable(true);
    }

    /**
     * checks if buttons are disabled,
     * re-enables them if they are
     */
    static void enableButtons() {
        if (next.isDisabled()) next.setDisable(false);
        if (prev.isDisabled()) prev.setDisable(false);
        if (del.isDisabled()) del.setDisable(false);
    }
}