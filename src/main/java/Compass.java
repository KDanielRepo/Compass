import javafx.scene.control.Label;
import javafx.scene.layout.Background;

public class Compass extends Label{
    private Double northPositionX = 0.0;
    private Double northPositionY = 0.0;

    public enum ROSE{
        one("one.png"),
        two("two.png");
        String url;
        private ROSE(String url){
            this.url = url;
        }
    }
    public void setNorthPosition(){
        this.setTranslateX(getNorthPositionX());
        this.setTranslateY(getNorthPositionY());
    }
    public void setBackgroundColor(){
        this.setStyle("-fx-background-color: #3D3D3D;");
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

    public void setnorthPositionY(Double northPositionY) {
        this.northPositionY = northPositionY;
    }

    public Label getLabel() {
        return this;
    }

    public void setLabel(Label north) {

    }

}
