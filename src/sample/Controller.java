package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
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
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private EventHandler myColorHandler;
    private double clickX;
    private double clickY;
    private javafx.scene.shape.Shape shape;
    private javafx.scene.transform.Scale scale;

    @FXML
    public void initialize(){

        myCanvas.setOnMousePressed(mouseEvent -> {
            if(myLine.isSelected()){
                javafx.scene.shape.Line line = new Line(mouseEvent.getX(), mouseEvent.getY(),  mouseEvent.getX(),mouseEvent.getY() );
                followLine(line);

            }else if(myRectangle.isSelected()){
                javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(mouseEvent.getX(), mouseEvent.getY(), 0.0,0.0);
                followRectangle(rectangle);

            }else if(myEllipse.isSelected()){
                javafx.scene.shape.Ellipse ellipse = new javafx.scene.shape.Ellipse(mouseEvent.getX(), mouseEvent.getY(), 0.0, 0.0);
                followEllipse(ellipse);
            }

            clickX = mouseEvent.getX();
            clickY = mouseEvent.getY();
        });

        myLine.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("Line has been selected");
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
            }
        });

        EventHandler<ActionEvent> myDeleteHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(mySelect.isSelected() && shape!=null){
                    myCanvas.getChildren().remove(shape);
                }
                System.out.println("The delete button has been selected");
            }
        };

        EventHandler<ActionEvent> myColorHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(mySelect.isSelected() && shape!=null){
                    shape.setFill(myColorPicker.getValue());
                }
                System.out.println("The clone button has been selected");
            }
        };

        EventHandler<ActionEvent> myCloneHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(mySelect.isSelected() && shape!=null){
                    /*
                    Shape shapes = new Shape(shape) {
                        //TODO le constructeur
                    };
                    shapes.setLayoutX(shape.getLayoutX());
                    shapes.setLayoutY(shape.getLayoutY());
                    //shape = null;
                    shapes.setTranslateX(10);
                    shapes.setTranslateY(10);
                     */


                }
                System.out.println("The clone button has been selected");
            }
        };

        myDelete.setOnAction(myDeleteHandler);
        myClone.setOnAction(myCloneHandler);
        myColorPicker.setOnAction(myColorHandler);
    }


    public void followEllipse(javafx.scene.shape.Ellipse ellipse){
        ellipse.setFill(myColorPicker.getValue());
        ellipse.setStroke(Color.BLACK);
        myCanvas.setOnMouseDragged(mouseEvent -> {
            ellipse.setRadiusX(Math.abs(mouseEvent.getX() - clickX));
            ellipse.setRadiusY(Math.abs(mouseEvent.getY() - clickY));
        });
        ellipse.setOnMouseClicked(mouseEvent -> {
            if(ellipse.getScaleX()==1 && mySelect.isSelected()){
                if(shape == null){
                    ellipse.setScaleX(1.15);
                    ellipse.setScaleY(1.15);
                    shape = ellipse;
                }else if(shape.equals(ellipse)){
                    shape = null;
                    ellipse.setScaleX(1);
                    ellipse.setScaleY(1);
                }else{
                    shape.setScaleX(1);
                    shape.setScaleY(1);
                    ellipse.setScaleX(1.15);
                    ellipse.setScaleY(1.15);
                    shape = null;
                    shape = ellipse;
                }
            }else if(mySelect.isSelected()){
                ellipse.setScaleX(1);
                ellipse.setScaleY(1);
            }
        });
        myCanvas.getChildren().add(ellipse);
        ellipse.setOnMouseDragged(mouseevent -> {
            if(shape!=null && mySelect.isSelected() && shape.equals(ellipse)){
                //shape.setTranslateX(clickX-mouseevent.getX());
                //System.out.println(shape.getTranslateX());
                /*
                ellipse.setCenterX(mouseevent.getX());
                ellipse.setCenterY(mouseevent.getY());

                 */
            }
        });


    }

    public void followRectangle(javafx.scene.shape.Rectangle rectangle){
        rectangle.setFill(myColorPicker.getValue());
        rectangle.setStroke(Color.BLACK);
        myCanvas.setOnMouseDragged(mouseEvent -> {
            rectangle.setWidth(Math.abs(mouseEvent.getX() - clickX));
            rectangle.setHeight(Math.abs(mouseEvent.getY() - clickY));
        });
        rectangle.setOnMouseClicked(mouseEvent1 -> {
            if(rectangle.getScaleX()==1 && mySelect.isSelected()){
                if(shape==null){
                    rectangle.setScaleX(1.15);
                    rectangle.setScaleY(1.15);
                    shape = rectangle;
                }else if(shape.equals(rectangle)){
                    shape = null;
                    rectangle.setScaleX(1);
                    rectangle.setScaleY(1);
                }else{
                    shape.setScaleX(1);
                    shape.setScaleX(1);
                    rectangle.setScaleX(1.15);
                    rectangle.setScaleY(1.15);
                    shape = rectangle;
                }
            }else if(mySelect.isSelected()){
                rectangle.setScaleX(1);
                rectangle.setScaleY(1);
            }
        });
        myCanvas.getChildren().add(rectangle);

    }

    public void followLine(javafx.scene.shape.Line line){
        line.setStroke(myColorPicker.getValue());
        myCanvas.setOnMouseDragged(mouseEvent -> {
            line.setEndX(mouseEvent.getX());
            line.setEndY(mouseEvent.getY());
        });
        line.setOnMouseClicked(mouseEvent1 -> {
            if(line.getScaleX()==1 && mySelect.isSelected()){
                if(shape==null){
                    line.setScaleX(1.15);
                    line.setScaleY(1.15);
                    shape = line;
                }else if(shape.equals(line)){
                    shape = null;
                    line.setScaleX(1);
                    line.setScaleY(1);
                }else{
                    shape.setScaleX(1);
                    shape.setScaleX(1);
                    line.setScaleX(1.15);
                    line.setScaleY(1.15);
                    shape = line;
                }
            }else if(mySelect.isSelected()){
                line.setScaleX(1);
                line.setScaleY(1);
            }
        });
        myCanvas.getChildren().add(line);


    }

}
