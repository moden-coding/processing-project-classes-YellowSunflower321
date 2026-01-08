import processing.core.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
// import processing.sound.SoundFile;

public class App extends PApplet{
        int scene = 1;
        // String letter = random("a","b"); //random letter of the alphabet 
        Tile tile = new Tile(200,600, "a", this);
        int text = 1;
        ArrayList<Tile> list = new ArrayList<>();
        int numberOfTiles = 16;


    public static void main(String[] args)  {
        PApplet.main("App");
        
    }


    public void setup(){
        // room = loadImage("room.png");

    }

    public void settings(){
        size(1200,800);
    }

    public void draw(){
        background(0,70,50); //dark green

        // for(int i=0; i<16; i++){
            tile.makeTile();
            list.add(tile);
        // }


    if (scene==1){

            if(text==1){
                fill(255);
                textSize(50);
                text("Welcome!", 300,120,300,80);
            } else if(text==2){
                textSize(30);
                fill(255);
                text("Play games to gain sleep, food, and water.", 150,120,600,80);
            } else if(text==3){
                fill(255);
                textSize(50);
                text("Lets get started!", 240,120,400,80);
            }


        // image(bed,-50,250,300,470);



    } else if (scene==2){
        background(0,100,50);
    }

    }
    public void keyPressed(){
        if(key == ' '){
            text++;
        }
    }

    public void mousePressed(){
        int posX = tile.returnPositionX();
        int posY = tile.returnPositionY();
        if(mousePressed){
        for(Tile tile:list){
            tile.checkInside(mouseX, mouseY);
        }
        }
    }

    public void mouseReleased(){

    }




}
