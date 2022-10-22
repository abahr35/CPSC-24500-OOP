package com.company;

public class Piece {
    String piece_name;
    String piece_color;
    int x_cord;
    int y_cord;

    public Piece() {

    }


    public void Move(Board myBoard, int x, int y) {

    }
    public String getPiece_name() {
        return piece_name;
    }

    public void setPiece_name(String piece_name) {
        this.piece_name = piece_name;
    }

    public int getX_cord() {
        return x_cord;
    }

    public void setX_cord(int x_cord) {
        this.x_cord = x_cord;
    }

    public int getY_cord() {
        return y_cord;
    }

    public void setY_cord(int y_cord) {
        this.y_cord = y_cord;
    }

    public Piece(String piece_name, String piece_color) {
        this.piece_name = piece_name;
        this.piece_color=piece_color;
    }

}
