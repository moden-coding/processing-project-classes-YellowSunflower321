import java.util.ArrayList;

import processing.core.PApplet;

public class Tile {
    private float posX;
    private float posY;
    private String letter;
    private PApplet canvas;
    private boolean dragging = false;
    private float offsetX, offsetY;
    ArrayList<Tile> list = new ArrayList<>();
    private boolean checkConnection;
    private int tileBorderR;
    private int tileBorderG;
    private int tileBorderB;


    public Tile(float x, float y, String l, PApplet c){
    posX = x;
    posY = y;
    letter = l;
    canvas = c;
    checkConnection = false;
    tileBorderR = 255;
    tileBorderG = 255;
    tileBorderB = 255;

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
        return posX;

    }

    public void mousePressed(){
        if(canvas.mouseX>posX && canvas.mouseX<(posX+50) && canvas.mouseY>posY && canvas.mouseY<(posY+50)){ //if mouse is clicking tile
            dragging=true;
            offsetX = canvas.mouseX - posX;
            offsetY = canvas.mouseY - posY;
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

    public void checkDistance(){        
        if(checkConnection == true){
        for(Tile tileMoving:list){ 
            for(Tile tile2:list){
                float DistanceX = Math.abs(tileMoving.returnPositionX()-tile2.returnPositionX()+50);
                float DistanceY = Math.abs(tileMoving.returnPositionY()-tile2.returnPositionY()+50);
                if(DistanceX<10){
                    tileMoving.setPositionX(tileMoving.returnPositionX()+DistanceX);
                    tileMoving.setPositionY(tile2.returnPositionY());
                    changeR(0);
                    changeG(200);
                    changeB(0);

                } else if(DistanceY<10){
                    tileMoving.setPositionY(tileMoving.returnPositionY()+DistanceY);
                    tileMoving.setPositionX(tile2.returnPositionX());
                    changeR(0);
                    changeG(200);
                    changeB(0);
                }
            }
        }
    }
    }

    public void changeR(int r){
        tileBorderR=r;
    }
    public void changeG(int g){
        tileBorderG=g;
    }
    public void changeB(int b){
        tileBorderB=b;
    }

    public int getR(){
        return tileBorderR;
    }
    public int getG(){
        return tileBorderG;
    }
    public int getB(){
        return tileBorderB;
    }


}