package com.bahra;
import java.awt.*;

public class Ship {

    private Point location = new Point(4,4);
    private int Size;//squares that the ship takes
    private Boolean Direction = true;//false is vertical true is horiz



    //getter setters

    public void setLocation(Point location) {
        this.location = location;
        System.out.println(location.toString());
    }

    public Point getLocation() {
        return location;
    }

    public int getSize(){return Size;}
    public double getOrigin(){
        return getSize()/2;
    }

    public Boolean getDirection() {return Direction;}
    public void setDirection(Boolean direction) {Direction = direction;}

}
