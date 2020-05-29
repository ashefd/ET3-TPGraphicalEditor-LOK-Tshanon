package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

import java.awt.*;

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
    Canvas myCanvas;

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


    // On va initialiser les variables aleatoires
    @FXML
    public void initialize(){


        GraphicsContext gc = myCanvas.getGraphicsContext2D();


        myCanvas.setOnMouseClicked(mouseEvent -> {
            clickX = mouseEvent.getX();
            clickY = mouseEvent.getY();
            System.out.println(clickX);
            System.out.println(clickY);
        });


        myCanvas.setOnMouseDragged(mouseEvent -> {
            if(myLine.isSelected()){
                // fill
                /*
                javafx.scene.shape.Line a = new javafx.scene.shape.Line();
                a.setStartX(clickX);
                a.setStartY(clickY);
                a.setEndX(mouseEvent.getX());
                a.setEndY(mouseEvent.getY());

                 */
            }else if(myRectangle.isSelected()){

            }else if(myEllipse.isSelected()){

            }
            //gc.setFill(myColorPicker.getValue());
            //gc.fillRect(mouseEvent.getX(), mouseEvent.getY(), 10, 10);
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


    public void drawShape(Shape shape){

    }

    public void deleteShape(){

    }

    public void cloneShape(){

    }

}
