
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class SnakeFactory {


    public Entity initHead()
    {
        Entity head = new Entity(50,50,SnakeType.HEAD);
        head.setFill(Paint.valueOf("RED"));
        return head;
    }
    public Entity initBodyPart()
    {
        Entity bodyPart = new Entity(50,50,SnakeType.BODY);
        return bodyPart;
    }
    public Entity initBerry()
    {
        Entity berry = new Entity(50,50,SnakeType.BERRY);
        berry.setFill(new ImagePattern(SnakeGraphic.berry()));
        return berry;
    }


}

