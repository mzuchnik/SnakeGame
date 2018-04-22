import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;

public class SnakeGraphic {

    private static Image img = new Image("snake-graphics.png");;
    private static final int WIDTH=64, HEIGHT=64,SCALE=50/64;
    private static SnakeEntity snake;
    private static PixelReader pixelReader = img.getPixelReader();
    private static Image img2,img3;
    private static boolean a1,a2,a3,a4;
    private static int dir,dirV=2,dirH;
    private static int tDirV=2,tDirH=2;
    public SnakeGraphic(SnakeEntity snake)
    {
        this.snake=snake;
    }
    public static void headDown()
    {
        img2 = new WritableImage(pixelReader,4*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
        snake.getHead().setFill(new ImagePattern(img2));
    }
    public static void headUP()
    {
        img2 = new WritableImage(pixelReader,3*WIDTH,0,WIDTH,HEIGHT);
        snake.getHead().setFill(new ImagePattern(img2));
    }
    public static void headLEFT()
    {
        img2 = new WritableImage(pixelReader,3*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
        snake.getHead().setFill(new ImagePattern(img2));
    }
    public static void headRIGHT()
    {
        img2 = new WritableImage(pixelReader,4*WIDTH,0,WIDTH,HEIGHT);
        snake.getHead().setFill(new ImagePattern(img2));
    }
    public static void bodyFirstPart() {
        dir = SnakeGame.getDirection();
        dirV = SnakeGame.getDirection2();
        dirH = SnakeGame.getDirection3();

        if((dir==1 && dirH==3 && !a1))
        {
            img2 = new WritableImage(pixelReader,0*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            tDirV=1;
            a1=true;

        }else if((dir==1&& dirH==3 && a1))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            tDirV=1;
        }
        if((dir==1 && dirH==4 && !a2))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,2*HEIGHT,WIDTH,HEIGHT);
            a2=true;
            tDirV=1;

        }else if((dir==1  && dirH==4 && a2))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            tDirV=1;
        }
        if((dir==2 && dirH==3 && !a1))
        {
            img2 = new WritableImage(pixelReader,0*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            a1=true;
            tDirV=2;

        }else if((dir==2&& dirH==3 && a1))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            tDirV=2;
        }
        if((dir==2 && dirH==4 && !a2))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            a2=true;
            tDirV=2;

        }else if((dir==2  && dirH==4 && a2))
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            tDirV=2;
        }
        if(dir==3 && dirV==1 && !a3)
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            a3=true;
            tDirH=3;
        }else if(dir==3 && dirV==1 && a3)
        {
            img2 = new WritableImage(pixelReader,1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            tDirH=3;
        }
        if(dir==3 && dirV==2 && !a3)
        {
            img2 = new WritableImage(pixelReader,2*WIDTH,2*HEIGHT,WIDTH,HEIGHT);
            a3=true;
            tDirH=3;
        }else if(dir==3 && dirV==2 && a3)
        {
            img2 = new WritableImage(pixelReader,1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            tDirH=3;
        }
        if(dir==4 && dirV==1 && !a4)
        {
            img2 = new WritableImage(pixelReader,0*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            a4=true;
            tDirH=4;
        }else if(dir==4 && dirV==1 && a4)
        {
            img2 = new WritableImage(pixelReader,1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            tDirH=4;
        }
        if(dir==4 && dirV==2 && !a4)
        {
            img2 = new WritableImage(pixelReader,0*WIDTH,1*HEIGHT,WIDTH,HEIGHT);
            a4=true;
            tDirH=4;
        }else if(dir==4 && dirV==2 && a4)
        {
            img2 = new WritableImage(pixelReader,1*WIDTH,0*HEIGHT,WIDTH,HEIGHT);
            tDirH=4;
        }
        if(dir==3 && dirH==3)
        {
            a1=false;
            a2=false;
        }
        if(dir==4 && dirH==4)
        {
            a2=false;
            a1=false;
        }
        if(dir==2 && dirV==2)
        {
            a4=false;
            a3=false;
        }
        if(dir==1 && dirV==1)
        {
            a4=false;
            a3=false;
        }
        if(snake.getBody().size()==1) {
            snake.getBody().get(0).setFill(new ImagePattern(getTail()));
            snake.getBody().get(0).setIMG(getTail());
        }
        else {
            snake.getBody().get(0).setIMG(getTail());
            snake.getBody().get(0).setFill(new ImagePattern(img2));
        }

    }
    public static Image berry()
    {
        return img2 = new WritableImage(pixelReader,0*WIDTH,3*HEIGHT,WIDTH,HEIGHT);
    }
    public static Image getTail()
    {
        if(tDirV==1){
            img3 = new WritableImage(pixelReader,3*WIDTH,2*HEIGHT,WIDTH,HEIGHT);
        }
        else if(tDirV==2)
        {
            img3 = new WritableImage(pixelReader,4*WIDTH,3*HEIGHT,WIDTH,HEIGHT);
        }
        else if(tDirH==3)
        {
            img3 = new WritableImage(pixelReader,3*WIDTH,3*HEIGHT,WIDTH,HEIGHT);
        }
        else if(tDirH==4)
        {
            img3 = new WritableImage(pixelReader,4*WIDTH,2*HEIGHT,WIDTH,HEIGHT);
        }
        tDirV=0;
        tDirH=0;
        return img3;

    }


    public static int gettDirV() {
        return tDirV;
    }

    public static int gettDirH() {
        return tDirH;
    }
}

