package com.bahra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {

    private boolean planningPhase = true;
    private JPanel mainPanel;
    private JLabel[][] gridLabels;


    public JLabel[][] getGridLabels() {
        return gridLabels;
    }

    public void setGridLabels(JLabel[][] gridLabels) {
        this.gridLabels = gridLabels;
    }


    GUI(){
        build();

    }

    private void build(){
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
                panelHolder[i][j].setBackground(Color.blue);
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

    public void setGridLabelSymbol(int x, int y, char Symbol){
        gridLabels[x][y].setText(String.valueOf(Symbol));
    }
    public void setGridLabelColor(int x, int y ,Color tileColor){
        gridLabels[x][y].setBackground(tileColor);
    }

    public void setHasShip(int x, int y) {
        setGridLabelColor(x, y, Color.BLACK);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

        //System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 40:
                if (planningPhase){
                    System.out.println("Down");
                }

                break;
            case 39:
                System.out.println("Right");
                break;
            case 38:
                System.out.println("Up");
                break;
            case 37:
                if (planningPhase){
                    System.out.println("Left");

                }
                break;

        }
    }



}
