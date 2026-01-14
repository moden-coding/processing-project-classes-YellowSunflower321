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
    private int tileBorderR;
    private int tileBorderG;
    private int tileBorderB;
    private int selected;
    private boolean canDump;
    private ArrayList<String> letters;
    private Tile[][] squares = new Tile[24][16];


    public Tile(float x, float y, PApplet c) {
        posX = x;
        posY = y;
        canvas = c;
        tileBorderR = 255;
        tileBorderG = 255;
        tileBorderB = 255;
        randomLetter();
        selected = 1;
        canDump = false;
        ArrayList<String> letters = new ArrayList<>();

    }

    public float returnPositionX() {
        return posX;
    }

    public float returnPositionY() {
        return posY;
    }

    public float setPositionX(float changeX) {
        posX = changeX;
        return posX;
    }

    public float setPositionY(float changeY) {
        posY = changeY;
        return posY;

    }

    public void mousePressed() {
        if (canvas.mouseX > posX && canvas.mouseX < (posX + 50) && canvas.mouseY > posY
                && canvas.mouseY < (posY + 50)) { // if mouse is clicking tile
            dragging = true;
            offsetX = canvas.mouseX - posX;
            offsetY = canvas.mouseY - posY;
            selected++;
            // if (selected % 2 == 0) {
            // changeColor(255, 0, 0);
            // canDump = true;
            // } else
            if (selected % 2 == 1) {
                changeColor(255, 255, 255);
                canDump = false;
            }
        }
    }

    public void mouseDragged() {
        if (dragging == true) { // if tile is in screen)

            posX = canvas.mouseX - offsetX;
            posY = canvas.mouseY - offsetY;

            posX = canvas.constrain(posX, 0, canvas.width - 50); // ChatGPT
            posY = canvas.constrain(posY, 0, canvas.height - 50); // Tile can't go off screen
        }
    }

    public void mouseReleased() {
        if (dragging) {
            dragging = false;
            checkDistance();

        }

    }

    // } else if(DistanceY<10){
    // tileMoving.setPositionY(tileMoving.returnPositionY()-DistanceY);
    // tileMoving.setPositionX(tile2.returnPositionX());
    // tileMoving.changeColor(0,200,0);

    // }
    // }
    // }
    // }
    // }

    public void changeColor(int r, int g, int b) {
        tileBorderR = r;
        tileBorderG = g;
        tileBorderB = b;

    }

    public int getColor() {
        return canvas.color(tileBorderR, tileBorderG, tileBorderB);
    }

    public char randomLetter() {
        // ChatGPT
        Random rand = new Random();
        char capital = (char) ('A' + rand.nextInt(26));
        letter = String.valueOf(capital);
        return capital;

    }

    public String getLetter() {
        return letter;
    }

    public void dump() {
        if (canDump == true) {
            Random rand = new Random();
            char capital = (char) ('A' + rand.nextInt(26));
            letter = String.valueOf(capital);
        }
    }

    int smallestX = 2000;
    int smallestY = 2000;

    float gridOffsetX = 100;
    float gridOffsetY = 500;

    public void checkDistance() {
        posX = Math.round((posX - gridOffsetX) / 50.0f) * 50 + gridOffsetX;
        posY = Math.round((posY - gridOffsetY) / 50.0f) * 50 + gridOffsetY;
        
    }

    public boolean dragging() {
        if (dragging == true) {
            return true;
        } else {
            return false;
        }

    }

    public boolean tilePresent(int row, int col) {
        if (returnPositionX() == row * 50 && returnPositionY() == col * 50) {
            return true;

        } else {
            return false;
        }
    }

    public void checkLocation(){

    }

    public void checkForWord(){
        
    }

    

}