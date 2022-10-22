package com.company;

public class RegularPiece extends Piece{

    public void Move(Board b, int x, int y) {
        //code here
        var pieces = b.getPieces();
        if (piece_color.equals("R") && x >= getX_cord() && !b.pieceExists(x,y)) {
            setX_cord(x);
            setY_cord(y);
        } else if (piece_color.equals("W") && x <= getX_cord() && !b.pieceExists(x,y)) {
            setX_cord(x);
            setY_cord(y);
        } else {
            System.out.println("failed");
        }
    }

    public RegularPiece(String name, String color){
        super(name,color);
    }

}
