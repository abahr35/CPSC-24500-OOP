package com.bahra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {


    Player(){
        Board gameBoard = new Board();
        gameBoard.printBoard();
        setupBoard(gameBoard);
        System.out.println();
        gameBoard.printBoard();
    }

    public void setupBoard(Board gameBoard){
        gameBoard.placeTempShip(gameBoard.carrier);

    }
}
