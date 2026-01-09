import processing.core.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
// import processing.sound.SoundFile;


//get random letter on each tile
//if you stack tiles, you can take off one at a time

public class App extends PApplet{
        int scene = 1;
        // String letter = random("a","b"); //random letter of the alphabet 
        int text = 1;
        int numberOfTiles = 16;
        ArrayList<Tile> list = new ArrayList<>();
        int columns = 10;
        int rows = 2;
        int timer = millis()/1000;




    public static void main(String[] args)  {
        PApplet.main("App");
        
    }


    public void setup(){
        // room = loadImage("room.png");
        for (int r=0; r < rows; r++) {
            for (int c=0; c < columns; c++) {
                Tile tile = new Tile(c*100+100, r*80+600, "a", this);
                tile.setPositionX(c*100+100);
                tile.setPositionY(r*80+600);

                list.add(tile);
                strokeWeight(5);
                stroke(tile.getR(),tile.getG(),tile.getB());
                fill(253, 217, 181);

                rect(tile.returnPositionX(),tile.returnPositionY(),50,50);
                textSize(30);
                text("a",tile.returnPositionX(),tile.returnPositionY(),20,20);

            
        }
        

    }
}

    public void settings(){
        size(1200,800);
    }

    public void draw(){
        background(0,70,50); //dark green

    if (scene==1){
        textSize(30);
        text("Time: " + timer,1000,100,200,50);
        //keeps drawing tiles
        for (Tile t : list) {
            strokeWeight(5);
            stroke(t.getR(),t.getG(),t.getB());
            fill(253, 217, 181);
            rect(t.returnPositionX(), t.returnPositionY(), 50, 50);
            textSize(30);
            text("a",t.returnPositionX(),t.returnPositionY(),20,20);

            t.checkDistance();
        }


            if(text==1){
                fill(255);
                textSize(50);
                text("Welcome to Banananograms!", 300,120,1000,80);
            } else if(text==2){
                textSize(30);
                fill(255);
                text("Play games to gain sleep, food, and water.", 150,120,600,80);
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
        for(Tile s : list){
            s.mousePressed();
        }
    }

    public void mouseDragged(){
        for(Tile s : list){
            s.mouseDragged();
        }
      
    }
    public void mouseReleased() {
        for(Tile s : list){
            s.mouseReleased();
        }

    }


}
