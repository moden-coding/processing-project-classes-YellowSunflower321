import processing.core.PApplet;

public class Platform {
    private int posX;
    private int posY;
    private int sizeX;
    private PApplet canvas;

    public Platform(int posx, int posy, int sizex, PApplet c){
        posX=posx;
        posY=posx;
        sizeX=sizex;
        canvas = c;
        
    }

    public void makePlatform(){
        System.out.println("platform show");
        canvas.noStroke();
        canvas.fill(150,75,0);
        canvas.rect(posX,posY,sizeX,30);
    }


}
