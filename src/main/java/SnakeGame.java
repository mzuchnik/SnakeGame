import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class SnakeGame extends Application{

    public static final int W_WIDTH = 800,W_HEIGHT= 800;
    public static final int UP=1,DOWN=2,LEFT=3,RIGHT=4;
    private static int direction=DOWN;
    private static int direction2=DOWN;
    private static int direction3=RIGHT;
    private static int score=-10;
    private Label label = new Label("TEST");
    private SnakeEntity snake = new SnakeEntity();
    private Pane pane;
    private Scene scene;
    private Timeline timeline = new Timeline();
    private Entity berry;
    private Random rand = new Random(62);

    public SnakeGame() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane();
        label.setFont(Font.font("Verdana",20));
        label.setTextFill(Paint.valueOf("WHITE"));
        label.setMinSize(300,40);
        label.setLayoutY(10);
        label.setLayoutX(650);
        pane.setMinWidth(W_WIDTH);
        pane.setPrefWidth(W_WIDTH);
        pane.setMinHeight(W_HEIGHT);
        pane.setPrefHeight(W_HEIGHT);
        pane.setBackground(new Background(new BackgroundImage(new Image("snake-bg.png"), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
        scene = new Scene(pane,W_WIDTH,W_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Entity");
        primaryStage.setScene(scene);
        startGame();
        primaryStage.show();
    }
    public void startGame()
    {
        newGame();
        initPlayerControl();
    }
    public void newGame()
    {
        score=0;
        pane.getChildren().clear();
        timeline.stop();
        pane.getChildren().add(label);
        snake.getBody().clear();
        new SnakeGraphic(snake);
        SnakeGraphic.headDown();
        initBerry();
        initPlayer();
        gameLoop();
    }
    public void initPlayer()
    {
        pane.getChildren().add(snake.getHead());
        growUp();
    }

    // wywoluje gdy cukierek zostanie zebrany przez weza
    public void growUp()
    {
        label.setText("Wynik : "+String.valueOf(score));
        score+=10;
        Entity bodyPart = snake.getSnakeFactory().initBodyPart();
        snake.getBody().add(bodyPart);
        pane.getChildren().add(bodyPart);
    }
    //Cale cialo weza podaza za jego glowa
    private void moveBodyParts()
    {
        for(int i=snake.getBody().size()-1;i>=0;i--) {
            if (i == 0) {
                snake.getBody().get(i).setTranslateX(snake.getHead().getTranslateX());
                snake.getBody().get(i).setTranslateY(snake.getHead().getTranslateY());
            } else {
                if (i == snake.getBody().size() - 1) {

                    snake.getBody().get(i).setTranslateX(snake.getBody().get(i - 1).getTranslateX());
                    snake.getBody().get(i).setTranslateY(snake.getBody().get(i - 1).getTranslateY());

                    snake.getBody().get(i).setIMG(snake.getBody().get(i - 1).getTailIMG());
                    snake.getBody().get(i).setFill(new ImagePattern(snake.getBody().get(i - 1).getTailIMG()));


                } else {
                    snake.getBody().get(i).setTranslateX(snake.getBody().get(i - 1).getTranslateX());
                    snake.getBody().get(i).setTranslateY(snake.getBody().get(i - 1).getTranslateY());
                    snake.getBody().get(i).setFill(snake.getBody().get(i - 1).getFill());
                    snake.getBody().get(i).setIMG(snake.getBody().get(i - 1).getTailIMG());
                }
            }
        }
    }

    //Kontrola wezem okreslanie gdzie ma sie poruszać (góra, dół, itp...)
    private void initPlayerControl()
    {
        scene.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.DOWN)
                if(direction!=UP && direction!=DOWN)
                    direction=DOWN;
            if (event.getCode() == KeyCode.UP)
                if(direction!=UP && direction!=DOWN)
                    direction=UP;
            if (event.getCode() == KeyCode.LEFT)
                if(direction!=LEFT && direction!=RIGHT)
                    direction=LEFT;
            if (event.getCode() == KeyCode.RIGHT)
                if(direction!=LEFT && direction!=RIGHT)
                    direction=RIGHT;
            if(event.getCode() == KeyCode.SPACE)
            {
                if(timeline.getStatus().name()=="RUNNING")
                {
                    timeline.pause();
                }else
                if(timeline.getStatus().name()=="PAUSED")
                    timeline.playFromStart();
            }
        });
    }
    // sprawdza czy waz jest poza mapa jesli tak to wychodzi zdrugiej strony
    private void checkOutOfMap()
    {
        if(snake.getHead().getTranslateX()>750)
            snake.getHead().setTranslateX(0);
        if(snake.getHead().getTranslateX()<0)
            snake.getHead().setTranslateX(750);
        if(snake.getHead().getTranslateY()<0)
            snake.getHead().setTranslateY(750);
        if(snake.getHead().getTranslateY()>750)
            snake.getHead().setTranslateY(0);
    }
    //Sprawdza czy waz sie ugryzl
    private void checkBite()
    {
        for(int i=0;i<snake.getBody().size();i++)
        {
            if(snake.getBody().get(i).getTranslateX() == snake.getHead().getTranslateX() && snake.getBody().get(i).getTranslateY() == snake.getHead().getTranslateY())
            {
                newGame();
            }
        }
    }
    //petla gry
    private void gameLoop()
    {
        timeline = new Timeline(new KeyFrame(Duration.seconds(.12),event -> {
            checkBite();
            checkPickUpBerry();
            moveBodyParts();
            int x = (int)snake.getHead().getTranslateX();
            int y = (int)snake.getHead().getTranslateY();
            if(direction==UP)
            {
                direction2=direction;
                snake.getHead().setTranslateY(snake.getHead().getTranslateY()-50);
            }
            else if(direction==DOWN)
            {
                direction2=direction;
                snake.getHead().setTranslateY(snake.getHead().getTranslateY()+50);
            }
            else if(direction==LEFT)
            {
                direction3=direction;
                snake.getHead().setTranslateX(snake.getHead().getTranslateX()-50);
            }
            else if(direction==RIGHT)
            {
                direction3=direction;
                snake.getHead().setTranslateX(snake.getHead().getTranslateX()+50);
            }
            checkOutOfMap();
            SnakeGraphic.bodyFirstPart();
            updateGraphicHead();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void initBerry()
    {
        berry= snake.getSnakeFactory().initBerry();
        int x =rand.nextInt(16)*50;
        int y =rand.nextInt(16)*50;
        berry.setTranslateX(x);
        berry.setTranslateY(y);
        pane.getChildren().add(berry);
    }
    private void checkPickUpBerry()
    {
        if(snake.getHead().getTranslateY() == berry.getTranslateY() && snake.getHead().getTranslateX()==berry.getTranslateX())
        {
            growUp();
            int x= rand.nextInt(16)*50;
            int y = rand.nextInt(16) * 50;
            berry.setTranslateX(x);
            berry.setTranslateY(y);
        }
    }
    void updateGraphicHead()
    {
        if(direction==UP)
            SnakeGraphic.headUP();
        else if(direction==DOWN)
            SnakeGraphic.headDown();
        else if(direction==LEFT)
            SnakeGraphic.headLEFT();
        else if(direction==RIGHT)
            SnakeGraphic.headRIGHT();
    }

    public static int getDirection() {
        return direction;
    }

    public static int getDirection2() {
        return direction2;
    }

    public static int getDirection3() {
        return direction3;
    }
}

