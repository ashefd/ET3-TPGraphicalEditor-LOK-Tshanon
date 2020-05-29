package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    javafx.scene.control.RadioButton mySelect;

    @FXML
    javafx.scene.control.RadioButton myEllipse;

    @FXML
    javafx.scene.control.RadioButton myRectangle;

    @FXML
    javafx.scene.control.RadioButton myLine;

    @FXML
    javafx.scene.layout.Pane myCanvas;

    @FXML
    javafx.scene.control.ColorPicker myColorPicker;

    @FXML
    javafx.scene.control.Button myDelete;

    @FXML
    javafx.scene.control.Button myClone;

    private EventHandler myDeleteHandler;
    private EventHandler myCloneHandler;
    private double clickX;
    private double clickY;
    private javafx.scene.shape.Line line;
    private javafx.scene.shape.Rectangle rectangle;
    private javafx.scene.shape.Ellipse ellipse;
    private ArrayList<EventHandler> shapeHandler;

    @FXML
    public void initialize(){

        myCanvas.setOnMousePressed(mouseEvent -> {
            if(myLine.isSelected()){
                line = new Line(mouseEvent.getX(), mouseEvent.getY(),  mouseEvent.getX(),mouseEvent.getY() );
                myCanvas.getChildren().add(line);
            }else if(myRectangle.isSelected()){
                rectangle = new javafx.scene.shape.Rectangle(mouseEvent.getX(), mouseEvent.getY(), 0.0,0.0);
                myCanvas.getChildren().add(rectangle);
            }else if(myEllipse.isSelected()){
                ellipse = new javafx.scene.shape.Ellipse(mouseEvent.getX(), mouseEvent.getY(), 0.0, 0.0);
                myCanvas.getChildren().add(ellipse);
            }
            clickX = mouseEvent.getX();
            clickY = mouseEvent.getY();
            System.out.println(clickX);
            System.out.println(clickY);

        });

        myCanvas.setOnMouseDragged(mouseEvent -> {
            // On va faire "augmenter" la taille de la forme
            if(myLine.isSelected()){
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
                line.setStroke(myColorPicker.getValue());
            }else if(myRectangle.isSelected()){
                // Ne fonctionne pas pour rectangle
                rectangle.setWidth(Math.abs(mouseEvent.getX() - clickX));
                rectangle.setHeight(Math.abs(mouseEvent.getY() - clickY));
                rectangle.setFill(myColorPicker.getValue());
                rectangle.setStroke(Color.BLACK);
            }else if(myEllipse.isSelected()){
                ellipse.setRadiusX(Math.abs(mouseEvent.getX() - clickX));
                ellipse.setRadiusY(Math.abs(mouseEvent.getY() - clickY));
                ellipse.setFill(myColorPicker.getValue());
                ellipse.setStroke(Color.BLACK);
            }
        });

        myLine.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Line has been selected");
                // javafx.scene.shape.Line
                // dessiner
            }
        });

        myRectangle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Rectangle has been selected");
                // javafx.scene.shape.Rectangle
                // dessiner
            }
        });

        myEllipse.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Ellipse has been selected");
                // javafx.scene.shape.Ellipse
                // dessiner
            }
        });

        mySelect.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Ellipse has been selected");
                // TODO
            }
        });

        EventHandler<ActionEvent> myDeleteHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("The delete button has been selected");
            }
        };

        EventHandler<ActionEvent> myCloneHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("The clone button has been selected");
            }
        };

        myDelete.setOnAction(myDeleteHandler);
        myClone.setOnAction(myCloneHandler);




    }

}
