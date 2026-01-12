import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class Tile {
    private float posX;
    private float posY;
    private String letter;
    private PApplet canvas;
    private boolean dragging = false;
    private float offsetX, offsetY;
    private boolean checkConnection;
    private int tileBorderR;
    private int tileBorderG;
    private int tileBorderB;
    private int selected;
    private boolean canDump;

    public Tile(float x, float y, PApplet c){
    posX = x;
    posY = y;
    canvas = c;
    checkConnection = false;
    tileBorderR = 255;
    tileBorderG = 255;
    tileBorderB = 255;
    randomLetter();
    selected = 1;
    canDump = false;

    }

    public float returnPositionX(){
        return posX;
    }

    public float returnPositionY(){
        return posY;
    }

    public float setPositionX(float changeX){
        posX = changeX;
        return posX;
    }

    public float setPositionY(float changeY){
        posY = changeY;
        return posY;

    }

    public void mousePressed(){
        if(canvas.mouseX>posX && canvas.mouseX<(posX+50) && canvas.mouseY>posY && canvas.mouseY<(posY+50)){ //if mouse is clicking tile
            dragging=true;
            offsetX = canvas.mouseX - posX;
            offsetY = canvas.mouseY - posY;
            selected++;
            if(selected%2==0){
                changeColor(255,0,0);
                canDump=true;
            } else if(selected%2==1){
                changeColor(255, 255, 255);
                canDump=false;
            }
        }
    }

    public void mouseDragged(){
        if(dragging==true){ //if tile is in screen)
            
            posX=canvas.mouseX-offsetX;
            posY=canvas.mouseY-offsetY;

            posX = canvas.constrain(posX, 0, canvas.width - 50); //ChatGPT
            posY = canvas.constrain(posY, 0, canvas.height - 50); //Tile can't go off screen
        }
    }
    public void mouseReleased() {
        dragging = false;
        checkConnection = true;
    }

    public void checkDistance(ArrayList<Tile> list){        
        if(checkConnection == true){
        for(Tile tileMoving:list){ 
            for(Tile tile2:list){
                if(tileMoving == tile2){
                    continue;
                }

                float DistanceX = Math.abs((tileMoving.returnPositionX()+50)-tile2.returnPositionX());
                float DistanceY = Math.abs((tileMoving.returnPositionY()+50)-tile2.returnPositionY());
                if(DistanceX<10){
                    tileMoving.setPositionX(tileMoving.returnPositionX()-DistanceX);
                    tileMoving.setPositionY(tile2.returnPositionY());
                    tileMoving.changeColor(0,200,0);

                } else if(DistanceY<10){
                    tileMoving.setPositionY(tileMoving.returnPositionY()-DistanceY);
                    tileMoving.setPositionX(tile2.returnPositionX());
                    tileMoving.changeColor(0,200,0);

                }
            }
        }
    }
    }

    public void changeColor(int r, int g, int b){
    tileBorderR = r;
    tileBorderG = g;
    tileBorderB = b;

    }
    public int getColor(){
        return canvas.color(tileBorderR,tileBorderG,tileBorderB);
    }

    public char randomLetter(){
        //ChatGPT
        Random rand = new Random();
        char capital = (char) ('A' + rand.nextInt(26)); 
        letter = String.valueOf(capital);
        return capital;

    }
    
    public String getLetter(){
        return letter;
    }

    public void dump(){
        if(canDump==true){
        Random rand = new Random();
        char capital = (char) ('A' + rand.nextInt(26)); 
        letter = String.valueOf(capital);
        }
    }
    int smallestX = 2000;
    int smallestY = 2000;
    public void checkDistance2(){
        if(checkConnection){
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if(Math.abs(posX-row*50)<smallestX){
                    smallestX=row*50;
                }
                if(Math.abs(posY-col*50)<smallestY){
                    smallestY=col*50;
                }
            }
        }
        }
    }

}