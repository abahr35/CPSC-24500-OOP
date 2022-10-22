package com.company;


//what is  a square? What will it contain? A piece and it will be a specific color
//Fixing git
public class Square {
    Piece piece;
    String color;
    int x;
    int y;

    //

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getPieceName(){
        return piece.getPiece_name();
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Square(){

    }

    public Square(Piece piece, int x, int y, String col) {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
        this.setColor(col);
    }
}