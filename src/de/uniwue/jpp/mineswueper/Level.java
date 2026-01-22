package de.uniwue.jpp.mineswueper;

public class Level {
    public int width;
    public int height;
    public int mineCount;
    public String label;


    public Level(int width, int height, int mineCount, String label) {
        //throw new UnsupportedOperationException();
        this.width=width;
        this.height=height;
        this.mineCount=mineCount;
        this.label=label;
    }
    public Level(int width, int height, int mineCount) {

        //throw new UnsupportedOperationException();
        this.width=width;
        this.height=height;
        this.mineCount=mineCount;
        this.label="Custom";
    }

    public int getHeight() {

        //throw new UnsupportedOperationException();
        return height;
    }

    public int getWidth() {

        //throw new UnsupportedOperationException();
        return width;
    }

    public int getMineCount() {

        //throw new UnsupportedOperationException();
        return mineCount;
    }

    public String getLabel() {

        //throw new UnsupportedOperationException();
        return label;
    }

    @Override
    public String toString() {
      //  throw new UnsupportedOperationException();
        // <label>:(<width>x<height>) <mineCount> mines
        String widthStr = String.valueOf(width);
        String heightStr = String.valueOf(height);
        String minesStr= String.valueOf(mineCount);
        String str = label+":("+widthStr+"x"+heightStr+") " +minesStr+ " mines";
        return str;

    }

    public static Level getBeginner(){

        //throw new UnsupportedOperationException();

        Level beginnerLevel= new Level(9,9,10,"Beginner");
        return beginnerLevel;

    }

    public static Level getIntermediate(){

        //throw new UnsupportedOperationException();

        Level intermediateLevel = new Level(16,16,40, "Intermediate");
        return intermediateLevel;
    }

    public static Level getExpert(){
       // throw new UnsupportedOperationException();
        Level expertLevel = new Level(30,16,99, "Expert");
        return expertLevel;
    }
}
