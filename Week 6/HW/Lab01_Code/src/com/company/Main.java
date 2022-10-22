
package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here

        ArrayList<Piece> piece_col = new ArrayList<>();

        KingPiece red_k_1 = new KingPiece("_KR0-2_","R");
        RegularPiece red_1 = new RegularPiece("__R0-0_","R");
        Board myBoard = new Board();

        piece_col.add(red_1);
        piece_col.add(red_k_1);

        myBoard.setPieceOnSpace(red_1,0,0);
        myBoard.setPieceOnSpace(red_k_1,0,2);
        System.out.println(red_k_1.getX_cord() + " " +red_k_1.getY_cord());
        myBoard.showBoard();

        RegularPiece red_2 = new RegularPiece("__R0-2_","R");
        myBoard.setPieceOnSpace(red_2,1,3);
        myBoard.showBoard();

        piece_col.add(red_2);

        //valid movement
        red_1.Move(myBoard,1,1);
        myBoard.showBoard();

        //invalid movement
        red_1.Move(myBoard,2,1);
        myBoard.showBoard();

        //invalid, this is reverse
        red_1.Move(myBoard,0,0);
        myBoard.showBoard();

        //valid movement
        red_1.Move(myBoard,2,2);
        myBoard.showBoard();

        //move the king piece forward
        red_k_1.Move(myBoard,1,1);
        myBoard.showBoard();

        //move the king forward again
        red_k_1.Move(myBoard,2,0);
        myBoard.showBoard();

        //move the king back
        red_k_1.Move(myBoard,1,1);
        myBoard.showBoard();

        KingPiece white_k_1 = new KingPiece("_WRK-2_","W");
        RegularPiece white_1 = new RegularPiece("__W0-0_","W");

        piece_col.add(white_1);
        piece_col.add(white_k_1);

        myBoard.showBoard();

        myBoard.setPieceOnSpace(white_1,5,2);
        myBoard.showBoard();

        white_1.Move(myBoard,4,1);
        myBoard.showBoard();

        myBoard.setPieceOnSpace(white_k_1,7,1);
        myBoard.showBoard();

        white_k_1.Move(myBoard,6,0);
        myBoard.showBoard();
        
        Piece piece_holder = new Piece( );
        piece_holder = piece_col.get(0);
        //this code will be highlighted in error because you need to implement inheritance to use the polymorphism
        piece_holder.Move(myBoard,1,0);
    }
}
