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
   private boolean dumping = false;


   //increases probability of getting common letters (such as 'E')
   private String LetterBag =
   "EEEEEEEEEEE" +
   "AAAAAAAA" +
   "IIIIIIII" +
   "OOOOOOO" +
   "NNNNNN" +
   "RRRRRR" +
   "TTTTTT" +
   "LLLLL" +
   "SSSSS" +
   "UUUU" +
   "DDDD" +
   "GGG" +
   "BBB" + "CCC" + "MMM" + "PPP" +
   "FFF" + "HHH" + "VV" + "WW" + "YY" +
   "K" + "J" + "X" + "Q" + "Z";




   public Tile(float x, float y, PApplet c) {
       posX = x;
       posY = y;
       canvas = c;
       tileBorderR = 255;
       tileBorderG = 255;
       tileBorderB = 255;
       randomLetter();


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


   public void mousePressed(){
       //drags tiles if mousePressed
       if (canvas.mouseX > posX && canvas.mouseX < (posX + 50) && canvas.mouseY > posY && canvas.mouseY < (posY + 50)) { // if mouse is clicking tile
           dragging = true;
           offsetX = canvas.mouseX - posX;
           offsetY = canvas.mouseY - posY;
       }
   }


   public void mouseDragged() {
       if (dragging == true) { // if tile is in screen)
           //position follows mouse x and y
           posX = canvas.mouseX - offsetX;
           posY = canvas.mouseY - offsetY;


           posX = canvas.constrain(posX, 0, canvas.width - 50); // ChatGPT
           posY = canvas.constrain(posY, 0, canvas.height - 50); // Tile can't go off screen
      
       }
   }


   public void mouseReleased() {
       if (dragging) {
           dragging = false;
       }


   }


   //resets tile's border color
   public void changeColor(int r, int g, int b) {
       tileBorderR = r;
       tileBorderG = g;
       tileBorderB = b;


   }
  
   public boolean isGreen(){
       //check if tile's RGB is green
       if(tileBorderR==0 && tileBorderG==255 && tileBorderB==0){
           return true;
       } else{
           return false;
       }
   }


   public int getColor() {
       return canvas.color(tileBorderR, tileBorderG, tileBorderB);
   }


   public char randomLetter() {
   Random rand = new Random();
   int index = rand.nextInt(LetterBag.length());
   char randLetter = LetterBag.charAt(index);
   letter = String.valueOf(randLetter); //convert from char to string
   return randLetter;
}


   public String getLetter() {
       return letter;
   }
  


   int smallestX = 2000;
   int smallestY = 2000;


   float gridOffsetX = 100;
   float gridOffsetY = 500;


   public void checkDistance(Tile[][] grid) {
   //snap tile to closes square in grid
   float newX = Math.round((posX - gridOffsetX) / 50) * 50f + gridOffsetX;
   float newY = Math.round((posY - gridOffsetY) / 50) * 50f + gridOffsetY;


   posX = newX;
   posY = newY;
      
   }


   public boolean dragging() {
       if (dragging == true) {
           return true;
       } else {
           return false;
       }


   }


   public boolean dumped(){
       return dumping;
   }




   public void isDumping(Tile[][] grid, ArrayList<Tile> list){
               dumping = true;
               //add three tiles under dump button
               for(int c=20; c<23; c++){
               Tile tile = new Tile(c * 50, 100, canvas);
               tile.setPositionX(c * 50);
               tile.setPositionY(100);
               tile.randomLetter();
               list.add(tile);
  
               }
   }


   public boolean tilePresent(float row, float col) {
       //check if there is a tile at given row and col
       if (posX == row * 50 && posY == 800-(col * 50)) {
           return true;


       } else {
           return false;
       }
   }


  


}

