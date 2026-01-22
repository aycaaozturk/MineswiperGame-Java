package de.uniwue.jpp.mineswueper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RevealFieldsResult {

    public Collection<Field> fields;

    public enum RevealFieldState {
        FIELD_NOT_REVEALED, FIELDS_REVEALED, FOUND_MINE
    }
    // bir adet field: []
    // collection: [][][] gibi

    public RevealFieldsResult() {
      //  Erstellt eine neue RevealFieldsResult-Instanz, die das Ergebnis eines erfolglosen Klicks
        //  ohne Änderung des Spielfelds darstellt.
        this.fields= Collections.emptyList();

    }

    public RevealFieldsResult(Collection<Field> fields) {

       //Erstellt eine neue RevealFieldsResult-Instanz mit einer Menge aufgedeckter Felder.
//        if(fields !=null){
//            this.fields=fields;
//        }
        this.fields=fields;



    }

    public Collection<Field> getRevealedFields() {
       //Gibt die im Konstruktor übergebenen Felder zurück.
        // Achten Sie darauf, dass die zurückgegebene Collection unveränderbar (immutable) sein muss!
            return Collections.unmodifiableCollection(fields);

    }

    public RevealFieldState getState() {
//        Gibt den encodierten Status des Ergebnisses zurück. Folgende Fälle treten dabei ein:
//        Die übergebene Sammlung ist leer -> FIELD_NOT_REVEALED
//        Die übergebene Sammlung enthält eine Mine -> FOUND_MINE
//        Sonst -> FIELDS_REVEALED
        if(fields.isEmpty()){
            return RevealFieldState.FIELD_NOT_REVEALED;
        }
        else{
            for(Field f : fields){
                if(f.hasMine){
                    return RevealFieldState.FOUND_MINE;
                }
            }
            return RevealFieldState.FIELDS_REVEALED;
        }

}

}
