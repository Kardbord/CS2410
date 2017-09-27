package cs2410.assn6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * Kvarfordt-Tanner-Assn6
 * Created on 10/26/2016
 *
 * This class creates a drawing tablet window in which a user may
 * draw rectangles, ellipses, or freehand lines. The user
 * is also provided with functionality to edit the
 * fill, stroke, stroke width, and location in the window of a shape
 * at any given time; a shape may also be erased at any time.
 *
 * @author Tanner Kvarfordt
 * @version 2.0
 */
public class DrawingTablet extends Application {
    /**
     * used for performing calculations to
     * properly move shapes in edit mode,
     * as well as initially create the shapes
     */
    private static double origX;

    /**
     * used for performing calculations to
     * properly move shapes in edit mode,
     * as well as initially create the shapes
     */
    private static double origY;

    /**
     * used when creating or editing the location
     * of a Rectangle
     */
    private static Rectangle tmpRec;

    /**
     * used when creating or editing the location
     * of an Ellipse
     */
    private static Ellipse tmpEll;

    /**
     * used when creating or editing the location
     * of a Path
     */
    private static Path tmpPath;

    /**
     * toolbar pane used at the top of the main BorderPane
     */
    private static ToolPane toolpane;

    /**
     * provides the area within which a user may draw.
     */
    private static Pane drawPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        toolpane = new ToolPane();
        toolpane.setPadding(new Insets(10));
        toolpane.setMinWidth(650);
        toolpane.setAlignment(Pos.TOP_CENTER);

        drawPane = new Pane();
        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!toolpane.eraseBtnSelected() && !toolpane.editBtnSelected()) {
                    origX = event.getX();
                    origY = event.getY();
                }

                if (toolpane.rectBtnSelected()) {
                    tmpRec = new Rectangle();
                    setShapeSettings(tmpRec);
                    setShapeHandler(tmpRec);
                    tmpRec.setLayoutX(event.getX());
                    tmpRec.setLayoutY(event.getY());
                    drawPane.getChildren().add(tmpRec);
                } else if (toolpane.ellBtnSelected()) {
                    tmpEll = new Ellipse();
                    setShapeHandler(tmpEll);
                    setShapeSettings(tmpEll);
                    drawPane.getChildren().add(tmpEll);
                } else if (toolpane.freeBtnSelected()) {
                    tmpPath = new Path();
                    setPathSettings(tmpPath);
                    setPathHandler(tmpPath);
                    drawPane.getChildren().add(tmpPath);
                    tmpPath.getElements().add(new MoveTo(event.getX(), event.getY()));
                }
            }
        });
        drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolpane.rectBtnSelected()) {
                    if (event.getX() < origX) {
                        tmpRec.setLayoutX(event.getX());
                    }
                    if (event.getY() < origY) {
                        tmpRec.setLayoutY(event.getY());
                    }
                    tmpRec.setWidth(Math.abs(event.getX() - origX));
                    tmpRec.setHeight(Math.abs(event.getY() - origY));
                } else if (toolpane.ellBtnSelected()) {
                    tmpEll.setRadiusX(Math.abs(event.getX() - origX) / 2);
                    tmpEll.setRadiusY(Math.abs(event.getY() - origY) / 2);
                    tmpEll.setCenterX(origX + (event.getX() - origX) / 2);
                    tmpEll.setCenterY(origY + (event.getY() - origY) / 2);

                } else if (toolpane.freeBtnSelected()) {
                    tmpPath.getElements().add(new LineTo(event.getX(), event.getY()));
                }
            }
        });

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(drawPane);
        mainPane.setTop(toolpane);
        mainPane.setBottom(null);
        mainPane.setRight(null);
        mainPane.setLeft(null);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(drawPane.widthProperty());
        clip.heightProperty().bind(drawPane.heightProperty());
        drawPane.setClip(clip);

        Scene scene1 = new Scene(mainPane, 650, 600);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Drawing Tablet");
        primaryStage.show();
    }

    private static void setShapeSettings(Shape shape) {
        shape.setFill(toolpane.getFillPickerValue());
        shape.setStroke(toolpane.getStrokePickerValue());
        shape.setStrokeWidth(toolpane.getStrokeSizeValue());
    }

    private static void setShapeHandler(Shape shape) {
        shape.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolpane.editBtnSelected()) {
                    origX = event.getX();
                    origY = event.getY();

                    //set picker/stroke event handlers
                    toolpane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (toolpane.editBtnSelected()) shape.setStroke(toolpane.getStrokePickerValue());
                        }
                    });
                    toolpane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (toolpane.editBtnSelected()) shape.setStrokeWidth(toolpane.getStrokeSizeValue());
                        }
                    });
                    toolpane.setFillPickerAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (toolpane.editBtnSelected()) shape.setFill(toolpane.getFillPickerValue());
                        }
                    });

                    //Change picker and stroke values as necessary
                    toolpane.setStrokeSizeValue((int) shape.getStrokeWidth());
                    toolpane.setStrokePickerValue((Color) shape.getStroke());
                    toolpane.setFillPickerValue((Color) shape.getFill());

                } else if (toolpane.eraseBtnSelected()) {
                    drawPane.getChildren().remove(shape);
                }
            }
        });

        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolpane.editBtnSelected()) {
                    shape.setTranslateX(shape.getTranslateX() + event.getX() - origX);
                    shape.setTranslateY(shape.getTranslateY() + event.getY() - origY);
                }
            }
        });
    }

    private static void setPathSettings(Path path) {
        path.setStrokeWidth(toolpane.getStrokeSizeValue());
        path.setStroke(toolpane.getStrokePickerValue());
    }

    private static void setPathHandler(Path path) {
        path.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolpane.editBtnSelected()) {
                    origX = event.getX();
                    origY = event.getY();
                    toolpane.setFillPickerAction(null);

                    //set picker/stroke event handlers
                    toolpane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (toolpane.editBtnSelected()) path.setStroke(toolpane.getStrokePickerValue());
                        }
                    });
                    toolpane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (toolpane.editBtnSelected()) path.setStrokeWidth(toolpane.getStrokeSizeValue());
                        }
                    });

                    toolpane.setStrokePickerValue((Color) path.getStroke());
                    toolpane.setStrokeSizeValue((int) path.getStrokeWidth());

                } else if (toolpane.eraseBtnSelected()) {
                    drawPane.getChildren().remove(path);
                }
            }
        });

        path.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolpane.editBtnSelected()) {
                    path.setTranslateX(path.getTranslateX() + event.getX() - origX);
                    path.setTranslateY(path.getTranslateY() + event.getY() - origY);
                }
            }
        });
    }
}
