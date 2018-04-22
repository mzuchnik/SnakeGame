

import java.util.ArrayList;

public class SnakeEntity extends Entity {

    private Entity head;
    private ArrayList<Entity> body = new ArrayList<>();
    private SnakeFactory snakeFactory = new SnakeFactory();

    public SnakeEntity()
    {
        this.head = snakeFactory.initHead();
    }

    public Entity getHead() {
        return head;
    }

    public ArrayList<Entity> getBody() {
        return body;
    }

    public SnakeFactory getSnakeFactory() {
        return snakeFactory;
    }

}

