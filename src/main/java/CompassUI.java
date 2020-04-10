import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class CompassUI extends Application {

    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(800,800);
        final Compass compass = new Compass();
        VBox tools = new VBox();
        HBox labelBox = new HBox();
        HBox tfBox = new HBox();
        HBox buttonBox = new HBox();
        HBox chooser = new HBox();
        Label xPos = new Label("Pozycja X");
        xPos.setPrefWidth(100);
        Label yPos = new Label("Pozycja Y");
        yPos.setPrefWidth(100);
        final TextField xField = new TextField();
        xField.setPrefWidth(xPos.getPrefWidth());
        final TextField yField = new TextField();
        yField.setPrefWidth(yField.getPrefWidth());
        Button northButton = new Button("Ustaw pozycję północy");
        northButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compass.setNorthPositionX(Double.parseDouble(xField.getText()));
                compass.setNorthPositionY(Double.parseDouble(yField.getText()));
                compass.rotateNeedle();
            }
        });
        Button targetButton = new Button("Ustaw pozycję celu");
        targetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compass.setTargetPositionX(Double.parseDouble(xField.getText()));
                compass.setTargetPositionY(Double.parseDouble(yField.getText()));
                compass.rotateRose();
            }
        });
        Button needleChooser = new Button("Wybierz igłę");
        needleChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage st = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz igłę");
                File fl = fileChooser.showOpenDialog(st);
                compass.changeNeedle("file:///"+fl.getPath());
            }
        });
        Button roseChooser = new Button("Wybierz różę");
        roseChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage st = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz różę");
                File fl = fileChooser.showOpenDialog(st);
                compass.changeRose("file:///"+fl.getPath());
            }
        });

        labelBox.getChildren().addAll(xPos,yPos);
        tfBox.getChildren().addAll(xField,yField);
        buttonBox.getChildren().addAll(northButton,targetButton);
        chooser.getChildren().addAll(needleChooser,roseChooser);
        tools.getChildren().addAll(labelBox,tfBox,buttonBox,chooser);
        borderPane.setRight(tools);

        borderPane.setLeft(compass);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(CompassUI.class, args);
    }
}
