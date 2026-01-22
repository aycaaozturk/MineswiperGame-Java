package de.uniwue.jpp.mineswueper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Field {
    public Coordinate coord;
    boolean hasFlag;
    boolean hasMine;
    boolean isOpened;
    int neighbourMineCount;

    List<Field> neighbors = new ArrayList<>();

    public Field(Coordinate coord) {
        //   Erstellt eine neue Field-Instanz an der gegebenen Position.
        //   Die erstellte Instanz besitzt keine Mine, ist nicht geöffnet und besitzt keine Flagge.
        //   Die Anzahl der benachbarten Minen ist zunächst auf 0 gesetzt.
        this.coord=coord;
        this.hasMine=false;
        this.isOpened=false;
        this.hasFlag=false;
        this.neighbourMineCount=0;


    }

    public void addNeighbor(Field f){
        neighbors.add(f);
    }

    public List<Field> getNeighbors() {
        return neighbors;
    }

    public Coordinate getCoordinate() {

        //throw new UnsupportedOperationException();
        return coord;
    }

    public boolean hasFlag() {

        //throw new UnsupportedOperationException();
        return hasFlag;
    }

    public void setHasFlag(boolean flag) {

        // Setzt eine Flagge auf das Feld (true), oder entfernt diese (false).
        this.hasFlag=flag;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean mine) {
        this.hasMine=mine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        this.isOpened=opened;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        if(neighbourMineCount<0){
            throw new IllegalArgumentException("negative!");
        }
        this.neighbourMineCount= neighbourMineCount;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return hasFlag == field.hasFlag && hasMine == field.hasMine && isOpened == field.isOpened && neighbourMineCount == field.neighbourMineCount && Objects.equals(coord, field.coord) && Objects.equals(neighbors, field.neighbors);
    }


}
