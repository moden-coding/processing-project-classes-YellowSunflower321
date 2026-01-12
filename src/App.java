import processing.core.*;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random; //randomizer class
import java.io.PrintWriter; //for saving names to data file
// import processing.sound.SoundFile;

//change probability of getting each letter (i.e higher probability for e)
//add grid
//add instructions and high score page

public class App extends PApplet {
    int scene = 1;
    int text = 1;
    int numberOfTiles = 16;
    ArrayList<Tile> list = new ArrayList<>();
    int tileColumns = 10;
    int tileRows = 2;
    int gridColumns = 24;
    int gridRows = 16;
    int timer = 0;
    int timerHome = 0;
    int level = 1;
    int coins = 0;
    String typedText = "";
    ArrayList<String> names = new ArrayList<>();
    int lineMoves = 0;

    Tile[][] grid = new Tile[16][16];

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        // room = loadImage("room.png");
        for (int r = 0; r < tileRows; r++) {
            for (int c = 0; c < tileColumns; c++) {
                Tile tile = new Tile(c * 100 + 100, r * 80 + 600, this);
                tile.setPositionX(c * 100 + 100);
                tile.setPositionY(r * 80 + 600);
                tile.randomLetter();
                list.add(tile);

            }

        }

    }

    public void settings() {
        size(1200, 800);
    }

    public void draw() {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                strokeWeight(2);
                stroke(20, 90, 70);
                fill(0, 70, 50); // dark green
                rect(row * 50, col * 50, 50, 50);
                // if(squareIsEmpty()){
                // continue;
                // } else if(){

                // }
                System.out.println(grid[row][col]);

            }
        }
        if (scene == 1) {

            textSize(50);
            fill(255);
            text("Enter your name.", 430, 200, 1000, 80);
            text("Welcome to Banananograms!", 300, 120, 1000, 80);
            strokeWeight(2);
            fill(255);
            rect(400, 350, 400, 70);
            timerHome = millis() / 1000;
            if (timerHome % 2 == 0) {
                fill(0);
                line(410 + lineMoves * 20, 365, 410 + lineMoves * 20, 405);

            }
            fill(0);
            text(typedText, 410, 350, 500, 500);
            saveName(typedText); // adds typed text to data file

        } else if (scene == 2) {
            // grid for background
            // for (int r=0; r < gridRows; r++) {
            // for (int c=0; c < gridColumns; c++) {
            // line(r+50,c+50,10,800);
            // }
            // }

            timer = millis() / 1000 - timerHome; // seconds
            textSize(30);
            fill(255);
            text("Time: " + timer + " seconds", 50, 50, 1000, 50);
            text("Level: " + level, 1050, 50, 200, 50);
            text("Coins: " + coins, 1050, 100, 200, 50); // gain coins for every word

            // TIMER
            // if(timer==10){
            // text++;
            // }

            // COINS
            // if(timer%3==0){
            // coins++;
            // }

            // keeps drawing tiles
            for (Tile t : list) {
                strokeWeight(2);
                stroke(t.getColor());
                fill(253, 217, 181);
                rect(t.returnPositionX(), t.returnPositionY(), 50, 50);
                textSize(30);
                fill(101, 67, 33);
                text(t.getLetter(), t.returnPositionX() + 15, t.returnPositionY() + 5, 100, 100);
                t.checkDistance2();
                // t.checkDistance2(list);
            }

            if (text == 1) {
                fill(255);
                textSize(50);
                text("Welcome to Banananograms " + typedText + "!", 250, 300, 1200, 80);
                // } else if(text==2){
                // fill(255);
                // textSize(30);
                // text("Game over" + timer,1050,50,1000,100);

                // }

                // image(bed,-50,250,300,470);
            }

        }

    }

    public void keyPressed() {
        if (key == ' ') {
            text++;
        }
        if (key == 'd') {
            for (Tile t : list) {
                t.dump();
            }
        }

        if(key == 's'){
            System.out.println();
        }
        // ChatGPT
        if (scene == 1) {
            // BACKSPACE deletes a character
            if (key == BACKSPACE && typedText.length() > 0) {
                typedText = typedText.substring(0, typedText.length() - 1);
                lineMoves--;
            } else if (key >= 32 && key <= 126) { // printable characters
                typedText += key;
                lineMoves++;
            } else if (key == ENTER) {
                scene++;

            }
        }
    }

    public void mousePressed() {
        for (Tile s : list) {
            s.mousePressed();
        }
    }

    public void mouseDragged() {
        for (Tile s : list) {
            s.mouseDragged();
        }

    }

    public void mouseReleased() {
        for (Tile s : list) {
            s.mouseReleased();
        }
    }

    public void saveName(String name) {
        PrintWriter writer = createWriter("playerNames.txt");
        writer.println(name);

    }

}
