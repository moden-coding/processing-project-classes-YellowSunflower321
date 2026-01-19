import processing.core.*;
import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PApplet;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter; //for saving names to data file
import java.nio.file.Paths;



public class App extends PApplet {
    int scene = 1;
    int text = 1;
    int numberOfTiles = 16;
    ArrayList<Tile> list = new ArrayList<>();
    int tileColumns = 10;
    int tileRows = 2;
    float gridColumns = 24;
    float gridRows = 16;
    int timer = 0;
    int timerHome = 0;
    String typedText = "";
    ArrayList<String> names = new ArrayList<>();
    boolean showText = true;
    Tile[][] grid = new Tile[24][16];
    public ArrayList<Word> wordsInRow = new ArrayList<>();
    public ArrayList<Word> wordsInCol = new ArrayList<>();
    HashSet<String> dictionary = new HashSet<>();
    ArrayList<Tile> lettersListCol = new ArrayList<>();
    ArrayList<Tile> lettersListRow = new ArrayList<>();
    boolean buttonPressed = false;
    int score = 0;
    int highScore = 1000000;
    int wordCount = 0;
    String onOff = "Off";
    boolean timedMode = false;
    int timeLimit = 0;
    boolean keyLock = false;
    int dumpsLeft = 3;




    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        loadDictionary();
        makeTiles();

    }

    public void settings() {
        size(1200, 800);
    }

    public void draw() {

        // Draws grid
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                strokeWeight(2);
                stroke(20, 90, 70);
                fill(0, 70, 50); // dark green
                rect(row * 50.0f, col * 50.0f, 50.0f, 50.0f); 

            }
        }
        if (scene == 1) {

            textSize(50);
            fill(255);
            text("Enter your name.", 430, 200, 1000, 80);
            text("Welcome to Bananagrams!", 340, 120, 1000, 80);
            strokeWeight(2);
            fill(255);
            //text box
            rect(400, 350, 400, 70);
            timerHome = millis() / 1000;
            fill(0);
            text(typedText, 410, 350, 500, 500);
            //Instructions button
            makeButton(50, 700, 200, 50,"Instructions", 70);

            //Settings button
            makeButton(50, 600, 200, 50,"Settings", 95);

            

        } else if (scene == 2) {
            timer = millis() / 1000 - timerHome; // time from start of game
            makeButton(1000,50,150,50,"Dump",1030);  

            textSize(30);
            fill(255);
            text("Time: " + timer + " seconds", 50, 50, 1000, 50);
            text("Dump Limit: " + dumpsLeft, 50, 100, 1000, 50);


            // keeps drawing tiles
            for (Tile t : list) {
                strokeWeight(2);
                stroke(t.getColor());
                fill(253, 217, 181);
                rect(t.returnPositionX(), t.returnPositionY(), 50, 50);
                textSize(30);
                fill(101, 67, 33);
                text(t.getLetter(), t.returnPositionX() + 15, t.returnPositionY() + 5, 100, 100);

            }

            if (text == 1 && showText == true) {
                fill(255);
                textSize(50);
                text("Welcome to Bananagrams " + typedText + "!", 250, 300, 1200, 80);
                textSize(35);
                text("Click 'r' to restart and 'h' to go home.", 300, 370, 1200, 80);

            }
            //counts green tiles
            int greenTiles = 0;
            for(Tile t : list){
                if(t.isGreen()){
                    greenTiles++;
                }
            }
            //All words are correct and connected
            if(greenTiles==list.size() && allTilesTouching()){
                scene=5; //Game completed
                score = timer;
                saveScore(score);
                System.out.println("Score: " + score);
            }

            //Timed mode: game ends after time limit reached
            if(timedMode==true && timer>=timeLimit*60){
                    scene=6; //game over
            }

            //DUMP BUTTON
            makeButton(1000,50,150,50,"Dump",1030);
        }
            
        
        // INSTRUCTIONS PAGE
        else if(scene == 3){
            background(0, 70, 50);
            textSize(50);
            fill(255);
            text("Instructions", 450, 150, 1000, 1000);
            textSize(30);
            text("1. Click and drag the letters to make words horizontally and vertically.", 100, 300, 1000, 200);
            text("2. Switch one letter for three by dragging your letter to 'dump'.", 100, 400, 1000, 200);
            text("3. Check out your timed high score to see how fast you can get.", 100, 500, 1000, 200);
            text("4. Drag a tile to the dump box - you can trade 1 tile for 3.", 100, 600, 1000, 200);

            //'Back' returns to home screen
            makeButton(1000, 700, 100, 50,"Back", 1020);
            if(checkButton(1000, 700, 100, 50,"Back", 1020)==true){
                scene=1;
                buttonPressed=false;
            }

    //SETTINGS PAGE
    } else if(scene == 4){
        background(0, 70, 50);
        textSize(50);
        fill(255);
        text("Settings", 550, 150, 1000, 1000);
        textSize(30);
        //NUMBER OF TILES
        text("Number of Tiles: " + tileColumns*tileRows, 100, 300, 1000, 200);
        textSize(50);
        makeButton(400, 300, 50, 50, "<", 415);
        makeButton(470, 300, 50, 50, ">", 490);
        textSize(30);
        fill(255);
        //TIMED MODE
        text("Timed mode: " + onOff, 150, 400, 2000, 1000);
        makeButton(470, 400, 50, 50, "On", 475);
        makeButton(400, 400, 50, 50, "Off", 405);
        if(timedMode==true){
            textSize(30);
            fill(255);
            text("Time limit: " + timeLimit + " min", 150, 500, 3000, 1000);
            makeButton(400, 500, 50, 50, "<", 415);
            makeButton(470, 500, 50, 50, ">", 490);
        }
        makeButton(1000, 700, 100, 50,"Back", 1020);
            if(checkButton(1000, 700, 100, 50,"Back", 1020)==true){
                scene=1;
                buttonPressed=false;
            }

    //GAME COMPLETED PAGE
    } else if(scene == 5){
        background(0,70,50);
        textSize(50);
        fill(255);
        text("Game Complete!", 450, 150, 1000, 1000);
        textSize(35);
        text("Your Score: " + score + " seconds", 200, 300, 1000, 1000);
        text("High Score: " + highScore() + " seconds", 200, 400, 1000, 1000);
        text("Words: " + wordCount, 200, 500, 1000, 1000);

    //GAME OVER PAGE
    } else if(scene == 6){
        list.clear();
        makeTiles();
        background(0,70,50);
        textSize(50);
        fill(255);
        text("Game Over", 470, 150, 1000, 1000);
        textSize(30);
        text("Press 'h' to return home.", 425, 250, 1000, 1000);
        score = timer;
        text("Your Score: " + score + " seconds", 430, 350, 1000, 1000);
        text("High Score: " + highScore() + " seconds", 430, 450, 1000, 1000);
    }
    }


    //Remakes tiles based off number of rows and columns
    public void makeTiles(){
        list.clear();
                for (int r = 0; r < tileRows; r++) {
            for (int c = 0; c < tileColumns; c++) {
                Tile tile = new Tile(c * 100 + 100, r * 100 + 600, this);
                tile.setPositionX(c * 100 + 100);
                tile.setPositionY(r * 100 + 600);
                tile.randomLetter();
                list.add(tile);

            }

        }
    }

    public void keyPressed() {
    if(keyLock==false){
        if (key == ' ') {
            text++;
            keyLock=true;
        }
        }
        //HOME
        if(key == 'h' && (scene==2 || scene==6 || scene==5)){
            scene=1;
            //reset score and tiles
            list.clear(); 
            makeTiles();
            score=0;
            keyLock=true;


        }
        if(key == 'r' && scene==2){
            list.clear(); //clears list of tiles
            makeTiles();
            showText=true;
            timerHome=millis()/1000;
            score = 0;
            keyLock=true;

        }

        // ChatGPT
        if (scene == 1) {
            // BACKSPACE deletes a character
            if (key == BACKSPACE && typedText.length() > 0) {
                typedText = typedText.substring(0, typedText.length() - 1);
                keyLock=true;
            } else if (key >= 32 && key <= 126) { // printable characters
                typedText += key;
                keyLock=true;

            } else if (key == ENTER) {
                saveName(typedText); // adds typed text to data file
                scene++;
                keyLock=true;


            }

    }
}
    public void keyReleased(){
        keyLock = false;
    }

    public void mousePressed() {

        //Welcome text disappears when mouse clicked
        if(scene==2){
            showText = false;
        }
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
        buttonPressed = true;

        for (Tile s : list) {
            s.mouseReleased();
            //snaps tiles in place
            s.checkDistance(grid);
            s.changeColor(255, 255, 255);

        }
        clearGrid();
        updateGrid();

        //reset tile colors (to white)
        for (Tile t : list) {
            t.changeColor(255, 255, 255);
        }
        //check for words
        checkRows();
        checkColumns();

        //Decreases/increases number of tiles in settings
        if(checkButton(400, 300, 50, 50, "<", 415)){
            if((tileColumns-1)>=3){
            tileColumns-=1;
            list.clear();
            makeTiles();
            buttonPressed=false;

            }
        }
        if(checkButton(470, 300, 50, 50, ">", 490)){
        if((tileColumns+1)<=10){
            tileColumns+=1;
            list.clear();
            makeTiles();
            buttonPressed=false;

        }
        }

        if(checkButton(470, 400, 50, 50, "On", 475)){
            onOff = "On";
            timedMode = true;
            buttonPressed=false;

        }
        if(checkButton(400, 400, 50, 50, "Off", 405)){
            onOff = "Off";
            timedMode = false;
            buttonPressed=false;

        }
        //Increases/decreases time limit in settings
        if(checkButton(470, 500, 50, 50, ">", 490)){
        if((timeLimit+1)<=10){
            timeLimit++;
            buttonPressed=false;
        }
        }
        if(checkButton(400, 500, 50, 50, "<", 415)){
        if((timeLimit-1)>=1){
            timeLimit--;
            buttonPressed=false;
        }
        }
        
        //DUMP
        if(grid[20][1]!=null && grid[20][1].dumped()==false && dumpsLeft>0){ //if tile at dump button and dumps left
            grid[20][1].isDumping(grid, list);
            //remove dumped tile
            for(int i=0; i<list.size(); i++){
                    if(list.get(i).tilePresent(20,1)){
                        list.remove(i);
                    }
            }
            dumpsLeft--;
        }
        //check if buttons are pressed (and mouse released)
            if(checkButton(50, 600, 200, 50,"Settings", 95)==true){
                scene=4;
                buttonPressed=false;
            }            
            if(checkButton(50, 700, 200, 50,"Instructions", 70)==true){
                scene=3;
                buttonPressed=false;
            }

    }
    
    public void updateGrid() {
        for (Tile t : list) {
            int row = Math.round(t.returnPositionX() / 50);
            int col = Math.round(t.returnPositionY() / 50);

            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
                grid[row][col] = t;
            }
        }

    }

    public void clearGrid() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                //all tiles are "null"
                grid[r][c] = null;
            }
        }
    }

    public void saveName(String name) {
    //ChatGPT taught me how to print the name in a new line each time
    try {
        PrintWriter writer = new PrintWriter(
            new FileWriter(sketchPath("names.txt"), true) 
        );
        writer.println(name); // prints name in data file
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving name: " + e.getMessage());
    }

}

public void saveScore(int score) {
    try {
        PrintWriter writer = new PrintWriter(
            new FileWriter(sketchPath("highscore.txt"), true) 
        );
        writer.println(score); // prints name in data file
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving name: " + e.getMessage());
    }

}
public int highScore() {
    int highScore=10000;
        try(Scanner fileReader = new Scanner(Paths.get("highscore.txt"))){
            while(fileReader.hasNextLine()){
                String s = fileReader.nextLine();
                //compares scanned line to highscore and updates highscore
                if(Integer.valueOf(s)<highScore){
                        highScore = Integer.valueOf(s);
                        System.out.println("Highscore: " + highScore);
                    }
            }
       }catch(Exception e){
        System.out.println("Error: " + e.getMessage());
       }
       return highScore;

}
    
public boolean allTilesTouching() {

    int placed = 0;

    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            //checks if all tiles are connected to one tile
            if (grid[r][c] != null) {
                placed++;
                //ChatGPT
                boolean hasNeighbor =
                        (r > 0 && grid[r-1][c] != null) ||
                        (r < grid.length-1 && grid[r+1][c] != null) ||
                        (c > 0 && grid[r][c-1] != null) ||
                        (c < grid[0].length-1 && grid[r][c+1] != null);

                if (!hasNeighbor && placed > 1) {
                    return false; // isolated tile
                }
            }
        }
    }
    return true;
}

    public void checkRows() {
        for (int col = 0; col < grid[0].length; col++) {
            String word = ""; // reset word per row
            for (int row = 0; row < grid.length; row++) {
                //if tile present
                if (grid[row][col] != null) {
                        lettersListRow.add(grid[row][col]);
                        word += grid[row][col].getLetter(); //add letter of tile to word
                } else{

                        if (word.length() >= 2) { // if it's the length of a word
                            Word w = new Word(word, lettersListRow, this);                        
                            if(dictionary.contains(word.toLowerCase())){  //real word
                                w.changeWordColor(0,255,0, lettersListRow); //green
                                System.out.println("Real word! (row)");
                                wordsInRow.add(w); //add word to list of words
                                wordCount++;


                            } else{ //not real word
                                w.changeWordColor(255,0,0, lettersListRow); //red
                                System.out.println("Not real word (row)");
                            }                       
                        System.out.println(word);
                        } 
        
                    word = "";
                    wordsInRow.clear();
                    lettersListRow.clear();
                }
                }
            // at end of each row
            if (word.length() >= 2) {
                wordsInRow.add(new Word(word, lettersListRow, this));
            }
        }
    
}
                 

    public void checkColumns() {
        // 0 = no word, 1 = word (not real), 2 = word (real)
        for (int row = 0; row < grid.length; row++) {
            String word = ""; // reset word per column
            for (int col = 0; col < grid[0].length; col++) {
                //if letter present
                if (grid[row][col] != null) {
                        lettersListCol.add(grid[row][col]);
                        word += grid[row][col].getLetter(); //add letter to word

                } else {
                    if (word.length() >= 2) { // if length of word
                        Word w = new Word(word, lettersListCol, this);
                        if(dictionary.contains(word.toLowerCase())){
                            w.changeWordColor(0,255,0, lettersListCol);
                            System.out.println("Real word! (col)");
                            wordsInCol.add(w);
                            wordCount++;

                        } else{
                            w.changeWordColor(255,0,0, lettersListCol);
                            System.out.println("Not real word (col)");
                        }   
                        System.out.println(word);
                      
                    }
                    word = "";
                    wordsInCol.clear();
                    lettersListCol.clear();

                }
            }
    
    
            // at end of each row
            if (word.length() >= 2) {
                wordsInCol.add(new Word(word, lettersListCol, this));
            }
        }
    }



    public void readNames() {
        String[] names = loadStrings("names.txt");
        for (String n : names) {
            println(n);
        }
    }

    //ChatGPT:
    //Checks to see if dictionary file contains word
    public void loadDictionary(){
    try{
        Scanner scanner = new Scanner(new File("dictionary.txt"));
        while(scanner.hasNextLine()){
            dictionary.add(scanner.nextLine().trim().toLowerCase());
        }
        scanner.close();
    } catch(Exception e){
        System.out.println(e);
    }
    }

    public void makeButton(int x, int y, int widthX, int heightY, String text, int textX){
        strokeWeight(2);
        stroke(255);
        fill(244,194,194); //baby pink
        rect(x, y, widthX, heightY);
        textSize(30);
        fill(0,70,40); //dark green
        text(text, textX, y+heightY/2-20, 500, 500);


    }

    //checks if mouse is in button
    public boolean checkButton(int x, int y, int widthX, int heightY, String text, int textX){
        if(buttonPressed==true && mouseX>x && mouseX<(x+widthX) && mouseY>y && mouseY<(y+heightY)){
            System.out.println("Button pressed");
            return true;

        } else{
            return false;
        }
    }


}
