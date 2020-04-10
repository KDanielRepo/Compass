import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Compass extends Pane implements Serializable {

    private static final long serialVersionUID = 8042020L;
    private Double x = 200.0;
    private Double y = 200.0;
    private Double northPositionX = 0.0;
    private Double northPositionY = 0.0;
    private Double targetPositionX = 0.0;
    private Double targetPositionY = 0.0;
    private String roseImageURL;
    private Image roseImage;
    private ImageView roseImageView;
    private String needleImageURL;
    private Image needleImage;
    private ImageView needleImageView;
    private double angle = 0.0;
    private String BGColor = "FFFFFF";

    public Compass(){
        this.setPrefSize(x,y);
        changeRose("defaultRose.png");
        changeNeedle("defaultNeedle.png");
    }
    public Compass(Double x, Double y){
        this.x = x;
        this.y = y;
        this.setPrefSize(x,y);
        changeRose("defaultRose.png");
        changeNeedle("defaultNeedle.png");
    }
    public Compass(String roseImageURL, String needleImageURL){
        this.setPrefSize(x,y);
        changeRose(roseImageURL);
        changeNeedle(needleImageURL);
    }

    /**
     * Należy wywołać po każdej zmianie pozycji północy (northPositionX lub northPositionY)
     * aby obrócić igłą
     */
    public void rotateNeedle(){
        double centerX =  x/2;
        double centerY =  y/2;
        double angle =  Math.toDegrees(Math.atan2(northPositionY - centerY, northPositionX - centerX))+90;
        needleImageView.setRotate(angle);
    }
    /**
     * Należy wywołać po każdej zmianie pozycji celu (targetPositionX lub targetPositionY)
     * aby obrócić różą wiatrów
     */
    public void rotateRose(){
        double centerX =  x/2;
        double centerY =  y/2;
        double angle =  Math.toDegrees(Math.atan2(targetPositionY - centerY, targetPositionX - centerX))+90;
        roseImageView.setRotate(angle);
    }

    /**
     * Zwracana jest wartość kąta pomiędzy północą a celem liczona zgodnie z ruchem wskazówek zegara
     *
     * @return
     */
    public double getAngleBetween(){
        double centerX =  x/2;
        double centerY =  y/2;
        double angleNorth = Math.toDegrees(Math.atan2(northPositionY - centerY, northPositionX - centerX));
        double angleTarget =  Math.toDegrees(Math.atan2(targetPositionY - centerY, targetPositionX - centerX));
        return angle = Math.abs(angleNorth)+Math.abs(angleTarget);
    }

    /**
     * Do zmiany róży podajemy ścieżkę do wybranej grafiki
     * @param roseImageURL
     */
    public void changeRose(String roseImageURL){
        if(roseImageView!=null){
            this.getChildren().remove(roseImageView);
        }
        this.roseImageURL = roseImageURL;
        roseImage = new Image(roseImageURL);
        roseImageView = new ImageView(roseImage);
        roseImageView.setFitWidth(x);
        roseImageView.setFitHeight(x);
        this.getChildren().add(roseImageView);
    }

    /**
     * Do zmiany igły podajemy ścieżkę do wybranej grafiki
     * @param needleImageURL
     */
    public void changeNeedle(String needleImageURL){
        if(needleImageView!=null){
            this.getChildren().remove(needleImageView);
        }
        this.needleImageURL = needleImageURL;
        needleImage = new Image(needleImageURL);
        needleImageView = new ImageView(needleImage);
        needleImageView.setFitWidth(x);
        needleImageView.setFitHeight(y);
        this.getChildren().add(needleImageView);
    }

    /**
     * Aby zmienić kolor tła podajemy w Stringu wartość HEX koloru
     * np: "FF00FF"
     * @param color
     */
    public void changeBackgroundColor(String color){
        this.setStyle("-fx-background-color: #"+BGColor);
    }

    /**
     * Podajemy ile krotnie większe mają być wszystkie elementy komponentu
     * Jeżeli chcemy wprowadzić zmianę tylko jednego wymiaru, jako parametr drugiego należy podać 0.0
     * @param x
     * @param y
     */
    public void changeScale(Double x, Double y){
        if(x!=0.0){
            this.x *=x;
        }
        if(y!=0.0){
            this.y *=y;
        }
        this.setPrefSize(this.x,this.y);
        if(roseImageView!=null){
            roseImageView.setFitWidth(this.x);
            roseImageView.setFitHeight(this.y);
        }
        if(needleImageView!=null){
            needleImageView.setFitWidth(this.x);
            needleImageView.setFitHeight(this.y);
        }
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
        this.setPrefSize(this.x,this.y);
        if(roseImageView!=null){
            roseImageView.setFitWidth(this.x);
        }
        if(needleImageView!=null){
            needleImageView.setFitWidth(this.x);
        }
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
        this.setPrefSize(this.x,this.y);
        if(roseImageView!=null){
            roseImageView.setFitHeight(this.y);
        }
        if(needleImageView!=null){
            needleImageView.setFitHeight(this.y);
        }
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

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }
}
