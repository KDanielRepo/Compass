import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CompassUI extends Application {
    boolean needle = false;
    public void start(Stage primaryStage) throws Exception {
        final Compass compass = new Compass();

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        for(Compass.Rose a : Compass.Rose.values()){
            comboBox.getItems().add(a);
        }
        comboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                compass.setRose((Compass.Rose) newValue);
            }
        });
        //listenery combo
        for(Compass.BGColor color : Compass.BGColor.values()){
            comboBox2.getItems().add(color);
        }
        comboBox2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                compass.setBackgroundColor((Compass.BGColor) newValue);
            }
        });
        final Button button = new Button("Needle");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                needle = !needle;
                if(needle){
                    button.setText("Rose");
                    compass.rotateNorth(compass.getNorthPositionX(),compass.getNorthPositionY());
                }else{
                    button.setText("Needle");
                    compass.rotateTarget(compass.getTargetPositionX(),compass.getTargetPositionY());
                }
            }
        });
        vBox.getChildren().addAll(comboBox,comboBox2,button);
        borderPane.setPrefSize(200,200);
        Scene scene = new Scene(borderPane);
        borderPane.setCenter(compass);
        borderPane.setRight(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(CompassUI.class,args);
    }
}
