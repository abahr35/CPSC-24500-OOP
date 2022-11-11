package com.bahra;

public class Tile{
    private char currentSymbol;
    private String currentColor;
    private static final char tempShip = 0x25A2;
    private static final char hasShip = 0x25A3;
    private static final char water = 0x25A7;

    Tile() {
        this(water, TextColor.TEXT_BLUE);
    }//overloaded constructor with default

    Tile(char currentSymbol, String currentColor){
        this.currentSymbol = currentSymbol;
        this.currentColor = currentColor;
    } //constructor
    private void setCurrentSymbol() {this.currentSymbol = Tile.hasShip;} //set symbol of Tile
    private void setCurrentColor(String color){this.currentColor = color;}

    public char getCurrentSymbol() {
        return currentSymbol;
    }

    public void setCurrentSymbol(char currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    public String printTile(){
        return currentColor + currentSymbol + TextColor.TEXT_RESET;} //print the current tile

    public void setHasShip(){
        setCurrentSymbol();
        setCurrentColor(TextColor.TEXT_BLACK);

    }
}
