import processing.core.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
// import processing.sound.SoundFile;

public class App extends PApplet{
        PImage brickwallImage;
        int scene = 1;
        Platform platform1 = new Platform(0,570,900, this);
        Platform platform2 = new Platform(0,490,700, this);
        Platform platform3 = new Platform(100,410,700, this);



    public static void main(String[] args)  {
        PApplet.main("App");

    }

    public void setup(){
        // brickwallImage = loadImage("brickwall.jpg");


    }

    public void settings(){
        size(800,600);
    }

    public void draw(){
        // image(brickwallImage, 100, 500, 100,100); //Image of wall
        if (scene==1){
            
            platform1.makePlatform();
            platform2.makePlatform();
            platform3.makePlatform();
        }



    }




}
