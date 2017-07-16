package gui;

import java.util.ArrayList;
import java.util.Scanner;

public class GUImessage {
	String text;
	float x;
	float y;
	float cX = 0;
	float cY = 0;
	ArrayList<GUIcharacter> guiChars = new ArrayList<GUIcharacter>();
	ArrayList<GUIpanel> guiPans = new ArrayList<GUIpanel>();
	ArrayList<GUIpanel> guiTops = new ArrayList<GUIpanel>();
	ArrayList<GUIpanel> guiBots = new ArrayList<GUIpanel>();
	public GUImessage(String text, float x, float y){
		this.text = text;
		this.x = x;
		this.y = y;
		displayMessage();
	}
	
	private void displayMessage(){
		for(int i = 0 ; i < text.length(); i++){
			String currentChar = text.substring(i, i+1);
			currentChar = currentChar.toUpperCase();
			if(currentChar.equals("A")){
				guiChars.add(new GUIcharacter(x+cX,y,0));
			}
			if(currentChar.equals("B")){
				guiChars.add(new GUIcharacter(x+cX,y,1));
			}
			if(currentChar.equals("C")){
				guiChars.add(new GUIcharacter(x+cX,y,2));
			}
			if(currentChar.equals("D")){
				guiChars.add(new GUIcharacter(x+cX,y,3));
			}
			if(currentChar.equals("E")){
				guiChars.add(new GUIcharacter(x+cX,y,4));
			}
			if(currentChar.equals("F")){
				guiChars.add(new GUIcharacter(x+cX,y,5));
			}
			if(currentChar.equals("G")){
				guiChars.add(new GUIcharacter(x+cX,y,6));
			}
			if(currentChar.equals("H")){
				guiChars.add(new GUIcharacter(x+cX,y,7));
			}
			if(currentChar.equals("I")){
				guiChars.add(new GUIcharacter(x+cX,y,8));
			}
			if(currentChar.equals("J")){
				guiChars.add(new GUIcharacter(x+cX,y,9));
			}
			if(currentChar.equals("K")){
				guiChars.add(new GUIcharacter(x+cX,y,10));
			}
			if(currentChar.equals("L")){
				guiChars.add(new GUIcharacter(x+cX,y,11));
			}
			if(currentChar.equals("M")){
				guiChars.add(new GUIcharacter(x+cX,y,12));
			}
			if(currentChar.equals("N")){
				guiChars.add(new GUIcharacter(x+cX,y,13));
			}
			if(currentChar.equals("O")){
				guiChars.add(new GUIcharacter(x+cX,y,14));
			}
			if(currentChar.equals("P")){
				guiChars.add(new GUIcharacter(x+cX,y,15));
			}
			if(currentChar.equals("Q")){
				guiChars.add(new GUIcharacter(x+cX,y,16));
			}
			if(currentChar.equals("R")){
				guiChars.add(new GUIcharacter(x+cX,y,17));
			}
			if(currentChar.equals("S")){
				guiChars.add(new GUIcharacter(x+cX,y,18));
			}
			if(currentChar.equals("T")){
				guiChars.add(new GUIcharacter(x+cX,y,19));
			}
			if(currentChar.equals("U")){
				guiChars.add(new GUIcharacter(x+cX,y,20));
			}
			if(currentChar.equals("V")){
				guiChars.add(new GUIcharacter(x+cX,y,21));
			}
			if(currentChar.equals("W")){
				guiChars.add(new GUIcharacter(x+cX,y,22));
			}
			if(currentChar.equals("X")){
				guiChars.add(new GUIcharacter(x+cX,y,23));
			}
			if(currentChar.equals("Y")){
				guiChars.add(new GUIcharacter(x+cX,y,24));
			}
			if(currentChar.equals("Z")){
				guiChars.add(new GUIcharacter(x+cX,y,25));
			}
			if(currentChar.equals(" ")){
				guiChars.add(new GUIcharacter(x+cX,y,26));
			}
			cX += 10;
		}
	}
	
	public void initialize(){
		for(int i = 0; i < guiChars.size(); i++){
			guiChars.get(i).init();
		}
		cX = 0;
		for(int i = 0; i < guiChars.size()+2; i++){
			if(i == 0){
				guiPans.add(new GUIpanel(x-10, y, Panel.LEFT));
				guiPans.get(i).init();
			} else if( i == guiChars.size()+1){
				guiPans.add(new GUIpanel(x+cX, y, Panel.RIGHT));
				guiPans.get(i).Init();
			} else {
				guiPans.add(new GUIpanel(x+cX, y, Panel.FILL));
				guiPans.get(i).init();
				cX += 10;
			}
		}
		cX = 0;
		
		int middleSize = guiPans.size();
		for(int i = 0; i < middleSize; i++){
			if(i == 0){
				guiTops.add(new GUIpanel(x-10, y+15, Panel.TOP_LEFT_CORNER));
				guiTops.get(i).init();
			} else if( i == middleSize-1){
				guiTops.add(new GUIpanel(x+cX, y+15, Panel.TOP_RIGHT_CORNER));
				guiTops.get(i).Init();
			} else {
				guiTops.add(new GUIpanel(x+cX, y+15, Panel.TOP));
				guiTops.get(i).init();
				cX += 10;
			}
		}
		
		cX = 0;
		for(int i = 0; i < middleSize; i++){
			if(i == 0){
				guiBots.add(new GUIpanel(x-10, y-15, Panel.BOTTOM_LEFT_CORNER));
				guiBots.get(i).init();
			} else if( i == middleSize-1){
				guiBots.add(new GUIpanel(x+cX, y-15, Panel.BOTTOM_RIGHT_CORNER));
				guiBots.get(i).Init();
			} else {
				guiBots.add(new GUIpanel(x+cX, y-15, Panel.BOTTOM));
				guiBots.get(i).init();
				cX += 10;
			}
		}
	}
	
	public void draw(){
		for(int i = 0; i < guiTops.size(); i++){
			guiTops.get(i).draw();
		}
		for(int i = 0; i < guiBots.size(); i++){
			guiBots.get(i).draw();
		}
		for(int i = 0; i < guiPans.size(); i++){
			guiPans.get(i).draw();
		}
		for(int i = 0; i < guiChars.size(); i++){
			guiChars.get(i).draw();
		}
		
	
	}
}
