package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Board {
    //Fixing git
    ArrayList<Piece> redPieces = new ArrayList<Piece>();
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();

    Square[][] squares = new Square[8][8];

    public Board() {
        createEmptyBoard();
    }

    private void createEmptyBoard(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares[x][y] = new Square();
            }
            setAllBlank(x);
        }
    }

    public void setAllBlank(int row){
        for (int y=0;y<8;y++){
            Piece emptyPiece = new Piece("","");
            setPieceOnSpace(emptyPiece,row,y);
            emptyPiece.setPiece_name("__" + row + "-" + y + "__");
            //emptyPiece.setFullName("empty");
        }
    }


    public ArrayList<Piece> showBoard() {
        System.out.println("======================================================");
        //print first row
        ArrayList<Piece> tempList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(squares[x][y].getPieceName());
                tempList.add(squares[x][y].getPiece());
            }
            System.out.println();
        }
        return tempList;
    }
    //

    public void setPieceOnSpace(Piece piece, int x, int y ) {
        squares[x][y].setPiece(piece);
        piece.setY_cord(y);
        piece.setX_cord(x);
    }

    public void removePieceOnSpace(int x, int y){
        Piece emptyPiece = new Piece("","");
        setPieceOnSpace(emptyPiece,x,y);
        emptyPiece.setPiece_name("__" + x + "-" + y + "__");
    }

    public Boolean pieceExists(int x, int y){
        return Objects.equals(squares[x][y].getColor(), "W") || Objects.equals(squares[x][y].getColor(), "R");
    }
    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> tempList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tempList.add(squares[x][y].getPiece());
            }
        }
        return tempList;
    }

}