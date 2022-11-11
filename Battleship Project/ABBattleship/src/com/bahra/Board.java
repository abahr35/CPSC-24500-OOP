package com.bahra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Board extends JFrame implements KeyListener {
    private final Tile[][] tileSet;
    //public GUI battleGUI = new GUI(); //try to implement GUI

    Color water = Color.CYAN;
    Color ship = Color.BLACK;
    private boolean planningPhase = true;
    private JPanel mainPanel;
    private JLabel[][] gridLabels;
    public Carrier carrier = new Carrier();
    public JLabel[][] getGridLabels() {
        return gridLabels;
    }

    public void setGridLabels(JLabel[][] gridLabels) {
        this.gridLabels = gridLabels;
    }

    public void setGridLabelSymbol(int x, int y, char Symbol){
        gridLabels[x][y].setText(String.valueOf(Symbol));
    }
    public void setGridLabelColor(int x, int y ,Color tileColor){
        gridLabels[x][y].setBackground(tileColor);
    }

    public void setHasShip(Ship currentShip){
        Point point = currentShip.getLocation();
        if(currentShip.getDirection())//horiz
        {
            if (currentShip.getSize() == 3) {
                setGridLabelColor(point.x, point.y-1, ship);
                setGridLabelColor(point.x, point.y, ship);
                setGridLabelColor(point.x, point.y+1, ship);

            }
        }
    }
    public void removeHasShip(char Direction ,Ship currentShip){
        Point point = currentShip.getLocation();
        if(currentShip.getDirection())//horiz
        {
            if (currentShip.getSize() == 3) {
                setGridLabelColor(point.x, point.y-1, water);
                setGridLabelColor(point.x, point.y, water);
                setGridLabelColor(point.x, point.y+1, water);

            }
        }
    }
    //todo rotateship
    public void rotateShip(Ship currentShip){
        if (currentShip.getDirection()){//if horizontal

        }

    }

    public void moveShip(char movementDirection, Ship currentShip) {
        Point modifiedLoc;
        if (movementDirection == 'l') {
            modifiedLoc = new Point((int) currentShip.getLocation().getX(), (int) (currentShip.getLocation().getY() -1));
            if (canPlaceShip(modifiedLoc, currentShip)) {
                removeHasShip(movementDirection, currentShip);
                currentShip.setLocation(modifiedLoc);
                setHasShip(currentShip);
            }
        } else if (movementDirection == 'r') {
            modifiedLoc = new Point((int) currentShip.getLocation().getX(), (int) (currentShip.getLocation().getY() +1));
            if (canPlaceShip(modifiedLoc, currentShip)) {
                removeHasShip(movementDirection, currentShip);
                currentShip.setLocation(modifiedLoc);
                setHasShip(currentShip);
            }
        } else if (movementDirection == 'u') {
            modifiedLoc = new Point((int) currentShip.getLocation().getX() -1, (int) (currentShip.getLocation().getY()));
            if (canPlaceShip(modifiedLoc, currentShip)) {
                removeHasShip(movementDirection, currentShip);
                currentShip.setLocation(modifiedLoc);
                setHasShip(currentShip);
            }

        } else if (movementDirection == 'd') {
            modifiedLoc = new Point((int) currentShip.getLocation().getX()+1, (int) (currentShip.getLocation().getY()));
            if (canPlaceShip(modifiedLoc, currentShip)) {
                removeHasShip(movementDirection, currentShip);
                currentShip.setLocation(modifiedLoc);
                setHasShip(currentShip);
            }
        }
    }
    public void placeTempShip(Ship currentShip){
        setHasShip(currentShip);

    }
    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 40:
                System.out.println("Down");
                if (planningPhase) {
                    moveShip('d', carrier);
                }
                break;
            case 39:
                System.out.println("Right");
                if (planningPhase) {
                    moveShip('r', carrier);
                }
                break;
            case 38:
                System.out.println("Up");
                if (planningPhase) {
                    System.out.println("Left");
                    moveShip('u', carrier);
                }
                break;
            case 37:
                if (planningPhase) {
                    System.out.println("Left");
                    moveShip('l', carrier);
                }
            case 9:
                if(planningPhase){
                    System.out.println("Tab");
                    rotateShip(carrier);
                }
                break;
        }
    }
    Board() {
        tileSet = new Tile[10][10];//virtual board
        for (int i = 0; i < tileSet.length; i++) {
            for (int j = 0; j < tileSet[i].length; j++) {
                tileSet[i][j] = new Tile();
            }
        }
        buildGUI();// build GUI Elements
        for (int i = 0; i < getGridLabels().length; i++) {
            for (int j = 0; j < getGridLabels()[i].length; j++) {
                setGridLabelColor(i,j, water);
            }
        }

    }

    //TODO finalize ship out of bounds

    private boolean canPlaceShip(Point moveTo, Ship currentShip){ //validate that its in bounds then check for other ships
        int shipSize = currentShip.getSize();
        System.out.println("Size: " + shipSize);
        boolean shipDirection = currentShip.getDirection();

        if (shipDirection) {//horizontal
            System.out.println("Horizontal");
            if (moveTo.y - 1 < 0 || moveTo.y + 1 > gridLabels.length-1) {
                return false;
            } else if (moveTo.x < 0 || moveTo.x + 1 > gridLabels[currentShip.getLocation().x].length) {
                return false;
            }
            return true;
        }
        return true;
    }

    //todo was here check for spacing library
    public void printBoard(){
        System.out.println("  0  1 2  3 4 5  6  7 8 9");
        for (int i = 0; i < tileSet.length; i++) {
            System.out.print(i+ " ");
            for (int j = 0; j < tileSet[i].length; j++) {
                System.out.print(tileSet[i][j].printTile() + " ");
            }
            System.out.println();
        }
    }
    private void buildGUI(){
        mainPanel = new JPanel(new GridLayout(10,10, 0,0));


        //frame
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(900,900));
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.add(mainPanel);
        this.setVisible(true);
        this.setFocusTraversalKeysEnabled(false);

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);
        //add objects to panel
        gridLabels = new JLabel[10][10];

        //fill gridlabels array
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridLabels[i][j] = new JLabel("",SwingConstants.CENTER);
                gridLabels[i][j].setText(i + "," + j);
                gridLabels[i][j].setOpaque(true);
            }
        }

        //create grid pattern of empty objects for panels
        JPanel[][] panelHolder = new JPanel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panelHolder[i][j]=new JPanel(new BorderLayout());
                panelHolder[i][j].setSize(30,30);
                panelHolder[i][j].setOpaque(true);
                panelHolder[i][j].setBackground(water);
                mainPanel.add(panelHolder[i][j]);
            }
        }

        //add labels to grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panelHolder[i][j].add(gridLabels[i][j], BorderLayout.CENTER);

            }
        }


        this.pack();

    }
}

