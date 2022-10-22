package com.company;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {
    //Fixing git
    ArrayList<Piece> redPieces = new ArrayList<Piece>();
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    File defaultPath = new File("saveGame.json");
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

    public void save(){
        save(defaultPath);
    }
    public void save(File path) {
        Gson gson = new Gson();
        var jsonObj = gson.toJson(squares);
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(jsonObj);
            fileWriter.close();
        }catch (IOException e){
            System.out.println("File not found! Could not save!");
        }
    }

    public void load(){
        load(defaultPath);
    }

    public void load(File path){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(path);
            squares = gson.fromJson(fileReader, Square[][].class);
        }catch (IOException e){
            System.out.println("Failed to Load Json!");
        }
    }

    @Override
    public String toString() {
        return "Board[ " + "squares:" + Arrays.toString(squares) + " ]";
    }
}