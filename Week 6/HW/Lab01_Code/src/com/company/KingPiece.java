package com.company;

public class KingPiece extends Piece{

    public void Move(Board b, int x, int y){
        //code here
        if(!b.pieceExists(x,y)){
            setX_cord(x);
            setY_cord(y);
        }

    }

    public KingPiece(String name, String color){
        super(name,color);
    }
}