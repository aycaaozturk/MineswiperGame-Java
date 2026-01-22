package de.uniwue.jpp.mineswueper;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {

        //throw new UnsupportedOperationException();
        this.x=x;
        this.y=y;
    }

    public Coordinate(Coordinate old, int x, int y) {

        //throw new UnsupportedOperationException();
        this.x=old.x + x;
        this.y= old.y +y;
    }

    public int getX() {

        //throw new UnsupportedOperationException();
        return x;
    }

    public int getY() {

        //throw new UnsupportedOperationException();
        return y;
    }

    @Override
    public boolean equals(Object o) {
       if(o instanceof Coordinate == false){
           return false;
       }

       Coordinate oCoord = (Coordinate) o;

       return this.x== oCoord.x && this.y== oCoord.y;

    }

    @Override
    public int hashCode() {
        return (50*x)+y;
    }

    @Override
    public String toString() {

        //throw new UnsupportedOperationException();
        // (<x>/<y>)
        String xStr = String.valueOf(x);
        String yStr = String.valueOf(y);
        return "("+xStr+"/"+yStr+")";

    }
}
