package de.uniwue.jpp.mineswueper;

import java.util.*;

public class Board {                                       //0, 1 , 2 , 3 , 4      (x-1, y-1)
    public int width;                                      //1  a   b   c          (x, y-1)
    public int height;                                     //2  d   O   e          (x+1, y-1)
    public Collection<Coordinate> mines;                   //3  f   g   h          (x-1, y)
    public Collection<Field> fields = new ArrayList<>();   //4                     (x+1,y)
    //5                     (x-1, y+1)
//    public Coordinate coord;                                                     (x, y+1)
//    boolean hasFlag;                                                             (x+1, y+1)
//    boolean hasMine;
//    boolean isOpened;
//    int neighbourMineCount;

    public int[] moveX = {-1, 0, 1, -1, 1, -1, 0, 1};
    public int[] moveY = {-1, -1, -1, 0, 0, 1, 1, 1};
    public Collection<Field> fieldsWithMine = new ArrayList<>();

    public Map<Coordinate, Field> coordinatesMap = new HashMap<>();

    public Board(int width, int height, Collection<Coordinate> mines) {
        //Erstellt ein neues Spielfeld mit einer Breite von width und einer Höhe von height.
        // Damit gilt für die Koordinaten: 0 <= x < width und 0 <= y < height.
        // Auf die Felder der in mines gegebenen Koordinaten werden die Minen gelegt.
        // Die Koordinaten beginnen bei (0/0) in der oberen, linken Ecke. Vergessen Sie nicht,
        // anschließend für alle Felder die Anzahl der benachbarten Minen zu berechnen.
        this.width = width;
        this.height = height;
        this.mines = mines;
        for (int i = 0; i < width; i++) {
            for (int a = 0; a < height; a++) {
                Coordinate newCoord = new Coordinate(i, a);
                Field yeniField = new Field(newCoord);
                fields.add(yeniField);
                coordinatesMap.put(newCoord, yeniField);
            }
        }
        for (Field f : this.fields) {
            if (mines.contains(f.getCoordinate())) {
                f.setHasMine(true);                   //mayin olan yerleri hasmine= true yaptik
                fieldsWithMine.add(f);
            }

        }
        for (Field f : this.fields) {    //  bir adet field icin komsu sayicaz
            int neigbourNumber = 0;          // 0 ile basladik
            Coordinate fCoord = f.getCoordinate();    //f in koordinatini aldik, x y aldik
            int fx = fCoord.x;
            int fy = fCoord.y;


            for (int i = 0; i < 8; i++) {        //tüm hareket yönlerini dene
                int movedX = fx + moveX[i];      //1 adet hareket yönü icin
                int movedY = fy + moveY[i];
                if (0 <= movedX && movedX < width && 0 <= movedY && movedY < height) {   //gecerli bir koordinatsa
                    Coordinate neighourCoord = new Coordinate(movedX, movedY);           //o koordinati yarattik
                    f.addNeighbor(coordinatesMap.get(neighourCoord));
                    if (mines.contains(neighourCoord)) {                                   //icinde gercekten mayin varsa
                        neigbourNumber++;
                    }
                }


            }
            f.setNeighbourMineCount(neigbourNumber);


        }


    }

    public boolean hasWon() {
        //  Signalisiert das Ende eines Spieles: true, falls alle Felder ohne Mine geöffnet wurden
        //  und alle Felder mit Minen noch verdeckt sind.
        boolean won = true;
        for (Field f : this.fields) {
            if (f.hasMine == true && f.isOpened) {
                won = false;
            } else if (f.hasMine == false && f.isOpened == false) {
                won = false;
            }

        }
        return won;
        //  throw new UnsupportedOperationException();
    }

    public void flagField(Coordinate coordinate) {
        //   Markiert ein Feld an den gegebenen Koordinaten mit einer Flagge.  kapaliysa bayrak koy
        //   Ist bereits eine Flagge auf das Feld gesetzt, wird sie entfernt.   bayrak varsa kaldir
        //   Sollte das Feld bereits aufgedeckt sein, so hat diese Methode keine Auswirkung.   eger aciksa bir etkisi yok
        for (Field f : this.fields) {
            if (coordinate.equals(f.getCoordinate())) {
                if (f.isOpened == false && f.hasFlag == false) {  //bayrak koy
                    f.setHasFlag(true);

                } else if (f.isOpened == false && f.hasFlag == true) {
                    f.setHasFlag(false);
                }
            }
        }

        //  throw new UnsupportedOperationException();
    }

    public int getRemainingMines() {
        //    Berechnet die Anzahl der noch verbleibenden Minen.
        //    Dazu wird von der Gesamtzahl der Minen, die Anzahl der mit einer Flagge versehenen Felder abgezogen.
        int numberAllMines = mines.size();
        int flags = 0;
        for (Field f : this.fields) {
            if (f.hasFlag) {
                flags++;
            }
        }
        return numberAllMines - flags;
        //  throw new UnsupportedOperationException();
    }

    public Collection<Field> getMines() {
        // Liefert eine Sammlung aller Felder, die eine Mine enthalten.

        return fieldsWithMine;
        //  throw new UnsupportedOperationException();
    }

    public Collection<Field> getFields() {
        //Liefert eine unveränderliche Sammlung aller Felder.
        return Collections.unmodifiableCollection(this.fields);
        //  throw new UnsupportedOperationException();
    }

    public RevealFieldsResult revealFields(Coordinate coord) {
//        Feld hat eine Flagge oder ist bereits aufgedeckt => leeres Ergebnis zurückgeben (FIELD_NOT_REVEALED)
//        Sonst: Feld als aufgedeckt markieren
//        Feld ist eine Mine => Ergebnis mit diesem Feld zurückgeben (FOUND_MINE)
//        Feld ist keine Mine:
//        Feld hat Nachbarn, die vermint sind => Ergebnis mit diesem Feld zurückgeben (FIELDS_REVEALED)
//        Feld hat keine Nachbarn, die vermint sind:
//        Aufdecken aller flaggenfreien noch nicht geöffneten Felder, die mit diesem Feld benachbart sind.
//                Werden dabei wiederum Felder aufgedeckt, die keine Mine in ihrer Nachbarschaft haben,
//                soll für diese jeweils genauso verfahren werden.
//=> Ergebnis mit allen aufgedeckten Feldern zurückgeben (FIELDS_REVEALED)
        for (Field f : fields) {
            if (coord.equals(f.getCoordinate())) {   //o fieldi bulduk
                if (f.hasFlag || f.isOpened()) {        //bayrak varsa ya da acildiysa bos sonuc dön
//                    Collection<Field> oneField = new ArrayList<>();
//                    oneField.add(f);
//                    RevealFieldsResult returnThis = new RevealFieldsResult(oneField);
//
                    RevealFieldsResult leeresErgebnis = new RevealFieldsResult();
                    return leeresErgebnis;
                }
                f.setOpened(true);    //aufgedeckt isaretledik

                if (f.hasMine) {
                    Collection<Field> oneField = new ArrayList<>();
                    oneField.add(f);
                    RevealFieldsResult returnThis = new RevealFieldsResult(oneField);
                    return returnThis;

                } else {
                    if (f.neighbourMineCount > 0) {   //ergebnis mit diesem feld zurück
                        Collection<Field> oneField = new ArrayList<>();
                        oneField.add(f);
                        RevealFieldsResult returnThis = new RevealFieldsResult(oneField);
                        return returnThis;
                    } else {  //komsularinda mayin yok
                        //       Aufdecken aller flaggenfreien noch nicht geöffneten Felder, die mit diesem Feld benachbart
                        //         sind.
//                Werden dabei wiederum Felder aufgedeckt, die keine Mine in ihrer Nachbarschaft haben,
//                soll für diese jeweils genauso verfahren werden.
//=> Ergebnis mit allen aufgedeckten Feldern zurückgeben (FIELDS_REVEALED)
                        Collection<Field> aufgedeckt = new ArrayList<>();
                        aufgedeckt.add(f);

                        List<Coordinate> discoverList = new ArrayList<>();
                        discoverList.add(coord);
                        while (discoverList.size() > 0) {
                            Coordinate c = discoverList.remove(0);
                            int fx = c.x;
                            int fy = c.y;

                            for (int i = 0; i < 8; i++) {        //tüm hareket yönlerini dene
                                int movedX = fx + moveX[i];      //1 adet hareket yönü icin
                                int movedY = fy + moveY[i];
                                if (0 <= movedX && movedX < width && 0 <= movedY && movedY < height) {   //gecerli bir koordinatsa
                                    Coordinate neighourCoord = new Coordinate(movedX, movedY);      //komsunun koordinata ulastik
                                    for (Field a : fields) {
                                        if (neighourCoord.equals(a.getCoordinate())) {   //o fieldi bulduk
                                            if (a.hasFlag == false && a.isOpened == false) {
                                                a.setOpened(true);
                                                aufgedeckt.add(a);
                                                if (a.neighbourMineCount == 0) {
                                                    discoverList.add(a.coord);
                                                }
                                                //return eksik
                                            }
                                        }
                                    }


                                }
                            }
                        }


                        RevealFieldsResult returnThis = new RevealFieldsResult(aufgedeckt);
                        return returnThis;

                    }


                }


                //  throw new UnsupportedOperationException();
                // return new RevealFieldsResult();
            }
        }
        RevealFieldsResult leeresErgebnis = new RevealFieldsResult();
        return leeresErgebnis;
    }

    public RevealFieldsResult revealMultiClickFields(Coordinate coord) {
//  Mit dieser Methode ist es möglich alle Nachbarn des bereits offenen Feldes an den gegebenen Koordinaten aufzudecken.
//  Dies geschieht, wenn doppelt auf ein bereits offenes Feld geklickt wird. Dabei gelten folgende Regeln:
//  Ist das Feld noch nicht geöffnet, passiert nichts => leeres Ergebnis zurückgeben (FIELD_NOT_REVEALED).
//     Ist das Feld geöffnet und es grenzt an keine Mine an, passiert nichts => leeres Ergebnis zurückgeben (FIELD_NOT_REVEALED).
//  Ist das Feld geöffnet und es grenzt an mindestens eine Mine an, wird unterschieden:
//  Stimmt die Anzahl der Flaggen auf den Nachbarfeldern nicht mit der Anzahl der Minen überein,
//  passiert nichts => leeres Ergebnis zurückgeben (FIELD_NOT_REVEALED).
//  Stimmt die Anzahl der Flaggen auf den Nachbarfeldern mit der Anzahl der Minen überein,
//  werden alle noch nicht geöffneten Felder die KEINE Flagge haben aufgedeckt
//  (siehe revealFields-Methode) => Ergebnis mit allen aufgedeckten Feldern zurückgeben (FIELDS_REVEALED)
//  Achtung: Ist also mindestens eine Flagge falsch gesetzt, ist das Spiel verloren (FOUND_MINE), da Minen aufgedeckt werden.
//
        if (!coordinatesMap.containsKey(coord)) {
            RevealFieldsResult leeresErgebnis = new RevealFieldsResult();
            return leeresErgebnis;
        } else {
            Field f = coordinatesMap.get(coord);
            if (f.isOpened == false || (f.isOpened == true && f.neighbourMineCount == 0)) {
                RevealFieldsResult leeresErgebnis = new RevealFieldsResult();
                return leeresErgebnis;
            } else if (f.isOpened == true && f.neighbourMineCount > 0) {

                int fNeighbourMines = f.neighbourMineCount;
                int fFlaggedNeighbours = 0;
                for (Field ff: f.getNeighbors()) {
                    if (ff.hasFlag) {
                        fFlaggedNeighbours++;
                    }
                }
                if (fNeighbourMines != fFlaggedNeighbours) {
                    RevealFieldsResult leeresErg = new RevealFieldsResult();
                    return leeresErg;
                } else {   //komsu mayin sayisi = komsu bayrak sayisi
                    Collection<Field> returnTheseFields = new ArrayList<>();
                    for (Field fi : f.getNeighbors()) {
                        if (fi.isOpened == false && fi.hasFlag == false) {
                            RevealFieldsResult result =  revealFields(fi.coord);
                            for (Field grf: result.getRevealedFields()){
                                if (!returnTheseFields.contains(grf)) {
                                    returnTheseFields.add(grf);
                                }

                            }


                        }
                    }
                    RevealFieldsResult returnThis = new RevealFieldsResult(returnTheseFields);
                    return returnThis;
                }
            }
        }
        RevealFieldsResult leeresErgebnis = new RevealFieldsResult();
        return leeresErgebnis;

    }

}