import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Compass extends Pane {
    private Double x = 200.0;
    private Double y = 200.0;
    private Double northPositionX = 0.0;
    private Double northPositionY = 0.0;
    private Double targetPositionX = 0.0;
    private Double targetPositionY = 0.0;
    private ImageView roseImageView;
    private ImageView canvasView;
    private Image roseImage;
    private Label north;

    public enum Rose{
        ONE("one.png"),
        TWO("two.png");
        String url;
        Rose(String url){
            this.url = url;
        }
        public String getUrl(){
            return url;
        }
    }
    public enum BGColor{
        BLACK("#000000"),
        WHITE("#FFFFFF"),
        BLUE("#0000FF"),
        RED("#FF0000"),
        GREEN("#00FF00");
        private BGColor(String color){
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        String color;
    }
    public void rotateNorth(double x, double y){
        double centerX =  roseImage.getWidth()/2;
        double centerY =  roseImage.getHeight()/2;
        double angle =  Math.toDegrees(Math.atan2(y - centerY, x - centerX))+90;
        canvasView.setRotate(angle);
    }

    public void rotateTarget(double x, double y){
        double centerX =  roseImage.getWidth()/2;
        double centerY =  roseImage.getHeight()/2;
        double angle =  Math.toDegrees(Math.atan2(y - centerY, x - centerX))+90;
        roseImageView.setRotate(angle);
    }
    public void prepareNeedle(){
        Canvas canvas = new Canvas(roseImage.getWidth(),roseImage.getHeight());
        double centerX =  roseImage.getWidth()/2;
        double centerY =  roseImage.getHeight()/2;
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(5);
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setFill(Color.GREEN);
        double[] x = new double[]{centerX-10,centerX,centerX+10,centerX-10,centerX,centerX+10};
        double[] y = new double[]{centerY,20,centerY,centerY,(centerY*2)-20,centerY};
        graphicsContext.strokePolygon(x,y,6);
        graphicsContext.fillPolygon(x,y,6);
        Image image = canvas.snapshot(new SnapshotParameters(),new WritableImage((int)roseImage.getWidth(),(int)roseImage.getHeight()));
        canvasView = new ImageView(image);
        canvasView.setOpacity(0.50);
        if(!this.getChildren().contains(canvasView)){
            this.getChildren().add(canvasView);
        }else{
            this.getChildren().remove(canvasView);
            this.getChildren().add(canvasView);
        }
    }
    public Compass(){
        this.setPrefSize(x,y);
        roseImage = new Image(Rose.ONE.getUrl());
        roseImageView = new ImageView(roseImage);
        north = new Label("N");
        this.getChildren().addAll(roseImageView,north);
        handle();
        prepareNeedle();
    }
    public Compass(Double x, Double y, Rose rose, BGColor color){
        this.x = x;
        this.y = y;
        setRose(rose);
        setBackgroundColor(color);
    }
    public Compass(Rose rose){
        this.setPrefSize(x,y);
        roseImage = new Image(rose.getUrl());
        roseImageView = new ImageView(roseImage);
        north = new Label("N");
        this.getChildren().addAll(roseImageView,north);
        handle();
        prepareNeedle();
    }
    public void setBackgroundColor(BGColor color){
        this.setStyle("-fx-background-color: "+color.getColor());
    }
    public void setRose(Rose rose){
        this.getChildren().remove(roseImageView);
        roseImage = new Image(rose.getUrl());
        roseImageView = new ImageView(roseImage);
        this.getChildren().add(roseImageView);
        prepareNeedle();
    }
    public void setSize(double x, double y){
        roseImageView.setScaleX(x);
        roseImageView.setScaleY(y);
    }

    public void handle(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                north.setTranslateX(event.getX());
                north.setTranslateY(event.getY());
                northPositionX = event.getX();
                northPositionY = event.getY();
                targetPositionX = event.getX();
                targetPositionY = event.getY();
            }
        });
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getNorthPositionX() {
        return northPositionX;
    }

    public void setNorthPositionX(Double northPositionX) {
        this.northPositionX = northPositionX;
    }

    public Double getNorthPositionY() {
        return northPositionY;
    }

    public void setNorthPositionY(Double northPositionY) {
        this.northPositionY = northPositionY;
    }

    public Double getTargetPositionX() {
        return targetPositionX;
    }

    public void setTargetPositionX(Double targetPositionX) {
        this.targetPositionX = targetPositionX;
    }

    public Double getTargetPositionY() {
        return targetPositionY;
    }

    public void setTargetPositionY(Double targetPositionY) {
        this.targetPositionY = targetPositionY;
    }
}
