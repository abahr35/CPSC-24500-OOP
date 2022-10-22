package com.company;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Board {
    //Fixing git
    ArrayList<Piece> redPieces = new ArrayList<Piece>();
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    File defaultPath = new File("checkers.json");
    Square[][] squares = new Square[8][8];

    public Board() {
        createEmptyBoard();
    }

    private void createEmptyBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares[x][y] = new Square();
            }
            setAllBlank(x);
        }
    }

    public void setAllBlank(int row) {
        for (int y = 0; y < 8; y++) {
            Piece emptyPiece = new Piece("", "");
            setPieceOnSpace(emptyPiece, row, y);
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

    public void setPieceOnSpace(Piece piece, int x, int y) {
        squares[x][y].setPiece(piece);
        piece.setY_cord(y);
        piece.setX_cord(x);
    }

    public void removePieceOnSpace(int x, int y) {
        Piece emptyPiece = new Piece("", "");
        setPieceOnSpace(emptyPiece, x, y);
        emptyPiece.setPiece_name("__" + x + "-" + y + "__");
    }

    public Boolean pieceExists(int x, int y) {
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

    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                jsonArray.add(squares[i][j].toJsonObject());
            }
        }
        return jsonArray;
    }

    public void save() {
        save(defaultPath);
    }

    public void save(File path) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("squares:", toJsonArray());

        String serializedText = JSONObject.toJSONString(jsonObject);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(path))) {
            buffer.write(serializedText);
            buffer.flush();
        } catch (IOException e) {
            System.out.println("File Path Not Found! Save failed!");
        }
    }

    public void load() {
        load(defaultPath);
    }

    public void load(File path) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            JSONParser jsonParser = new JSONParser();
            //input data into parser
            var parsedJson = jsonParser.parse(buffer);
            JSONObject jsonObject = (JSONObject) parsedJson;
            //find square objects
            var foundOBJ = jsonObject.get("squares:");
            //parse array inside squares object
            JSONArray squareJSONArray = (JSONArray) foundOBJ;

            int j = 0;
            for (int i = 0; i < squares.length; i++) {
                squares[j][i] = new Square();
                JSONObject tempOBJ = (JSONObject) squareJSONArray.get(0);
                int tempInt = (int)tempOBJ.intValue;
                squares[j][i].setX(tempInt);
                tempInt = (int) squareJSONArray.get(1);
                squares[j][i].setY(tempInt);
                //convert the piece object from an array to object
                JSONArray pieceArray = (JSONArray) squareJSONArray.get(2);
                //one piece for every iteration
                Piece convertedPiece = new Piece();
                convertedPiece.setPiece_name(((String) pieceArray.get(0)));
                convertedPiece.setPiece_color((String) pieceArray.get(1));
                convertedPiece.setY_cord(((int) pieceArray.get(2)));
                convertedPiece.setX_cord(((int) pieceArray.get(3)));
                squares[j][i].setPiece(convertedPiece);
                squares[j][i].setColor((String) squareJSONArray.get(3));
                //advance the array after 1 loop
                j++;
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("File not found! Load Failed!");
        } catch (ParseException e) {
            System.out.println("Could not parse JSON file! Load Failed!");
        }
    }
}
