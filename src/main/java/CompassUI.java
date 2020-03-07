import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class CompassUI extends Application {
    Compass compass = new Compass();
    ImageView imageView;
    Rotate rotate;
    Double dl1 = 0.0;
    Double dl2 = 0.0;
    Double wek = 0.0;
    public void getDl(Double x, Double y){
        dl1 = Math.sqrt((x*x+y*y));
        //System.out.println(dl1);
    }
    public void getD2(Double x, Double y){
        dl2 = Math.sqrt((x*x+y*y));
        //System.out.println(dl2);
    }
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("N");
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(200,200);
        final Scene scene = new Scene(borderPane);
        compass.setLabel(label);
        final Image image = new Image("one.png");

        imageView = new ImageView(image);
        borderPane.setCenter(imageView);
        borderPane.setLeft(compass.getLabel());
        //compass.setBackgroundColor();
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                compass.setNorthPositionX(event.getX());
                compass.setnorthPositionY(event.getY());
                compass.setNorthPosition();
                getDl(event.getX(),event.getY());
                getD2(scene.getWidth()/2,scene.getHeight()/2);
                Double sin =  dl2/dl1;
                wek = event.getX()*scene.getWidth()/2+event.getY()*scene.getHeight()/2;
                //System.out.println(sin);
                System.out.println(Math.toDegrees(Math.sin(wek/sin)));
                rotate = Affine.rotate(Math.toDegrees(Math.cos(sin)),image.getWidth()/2,image.getHeight()/2);
                //System.out.println(scene.getWidth()+" "+event.getX());
                //System.out.println(Math.sin((scene.getWidth()/2)+(scene.getWidth()-event.getX())));
                if(imageView.getTransforms().size()==0){
                    imageView.getTransforms().addAll(rotate);
                }else{
                    imageView.getTransforms().set(0,rotate);

                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        //System.out.println(primaryStage.getWidth()/2+" "+primaryStage.getHeight()/2);
    }

    public static void main(String[] args) {
        launch(CompassUI.class,args);
    }
}
