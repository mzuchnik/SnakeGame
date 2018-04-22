

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity extends Rectangle{

    private SnakeType type;
    private Image IMG;

    public Entity() {
    }

    public Entity(double width, double height, SnakeType type) {
        super(width, height);
        this.type = type;
    }

    public SnakeType getType() {
        return type;
    }

    public void setType(SnakeType type) {
        this.type = type;
    }

    public void setIMG(Image tailIMG) {
        this.IMG = tailIMG;
    }

    public Image getTailIMG() {
        return IMG;
    }

}

