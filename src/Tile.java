import processing.core.PApplet;

public class Tile {
    private int posX;
    private int posY;
    private String letter;
    private PApplet canvas;
    private boolean free = false;

    public Tile(int x, int y, String l, PApplet c){
    posX = x;
    posY = y;
    letter = l;
    canvas = c;
    }

    public void makeTile(){
        canvas.fill(253, 217, 181);
        canvas.noStroke();
        canvas.rect(posX,posY,50,50);
    }

    public int returnPositionX(){
        return posX;
    }

    public int returnPositionY(){
        return posY;
    }

    public void mouseDrag(){
        
        canvas.rect(posX,posY,60,60);

    }

    public void checkInside(float x, float y){
        if(canvas.mouseX>posX && canvas.mouseX<(posX+50) && canvas.mouseY>posY && canvas.mouseY<(posY+50)){
            free=true;
        }
        if(free==true){
            posX=canvas.mouseX;
            posY=canvas.mouseY;
        }
    }



}