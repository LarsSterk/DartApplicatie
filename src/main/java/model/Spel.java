package model;

import java.util.ArrayList;

public class Spel {
    // Attributen
    private ArrayList<Speler> spelersLijst;
    private String type;
    private int stand;
    private ArrayList<Leg> legLijst;

    // Methods
    public void maakSpel() {

    }

    public void verwijderSpel() {

    }

    public void voegSpelerToe() {

    }

    public void updateStand() {

    }

    public void voegLegToe(){
        
    }

    @Override
    public String toString() {
        return "Spel{" +
                "spelersLijst=" + spelersLijst +
                ", type='" + type + '\'' +
                ", stand=" + stand +
                '}';
    }
}
