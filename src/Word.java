import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;


public class Word { 
    private String wordString;
    private PApplet canvas;

    public Word(String s, ArrayList<Tile> list, PApplet c){
        wordString = s;
        canvas = c;
    }

    public String getWord(){
        //get word
        return wordString;
    }

    public void changeWordColor(int r, int g, int b, ArrayList<Tile> list){
        //change color of all tiles in word
        for(Tile t:list){
            t.changeColor(r,g,b);
        }
    }


}