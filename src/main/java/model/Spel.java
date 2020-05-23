package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spel implements Serializable {
    // --- Attributen ---
    private int spelId;
    private List<Speler> spelersLijst = new ArrayList<>(); // Associatie naar Speler.java
    private String type;
    private int winnendAantalLegs;
    private ArrayList<Leg> legLijst; // Associatie naar Leg.java
    private ArrayList<Integer> stand; // ArrayList om de stand bij te houden, de ArrayList bevat Integers omdat het de score is.

    // --- Constructor ---
    public Spel(String type,  int winnendAantalLegs, List<Speler> spelersLijst) {
        this.spelersLijst = spelersLijst;
        this.type = type;
        this.winnendAantalLegs = winnendAantalLegs;
        this.legLijst = new ArrayList<>();
        this.stand  = new ArrayList<>();
        this.spelId = 0;

        for (Speler speler : spelersLijst) {
            stand.add(0);
        }

    }

    // --- Getters ---
    public List<Speler> getSpelersLijst() {
        return spelersLijst;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Integer> getStand() {
        return stand;
    }

    public ArrayList<Leg> getLegLijst() {
        return legLijst;
    }

    public int getWinnendAantalLegs() {
        return winnendAantalLegs;
    }

    public int getSpelId() {
        return spelId;
    }

    // --- Setters ---
    public void setSpelersLijst(ArrayList<Speler> spelersLijst) {
        this.spelersLijst = spelersLijst;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStand(ArrayList<Integer> stand) {
        this.stand = stand;
    }

    public void setLegLijst(ArrayList<Leg> legLijst) {
        this.legLijst = legLijst;
    }

    public void setWinnendAantalLegs(int winnendAantalLegs) {
        this.winnendAantalLegs = winnendAantalLegs;
    }

    public void setSpelId(int spelId) {
        this.spelId = spelId;
    }

    // --- Methods ---

    public void maakSpel() {

    }

    public void verwijderSpel() {

    }

    public void voegSpelerToe() {

    }

    public void updateStand() {

    }

    public void voegLegToe() {

    }

    @Override
    public String toString() {
        return "Spel{" +
                "spelId=" + spelId +
                ", spelersLijst=" + spelersLijst +
                ", type='" + type + '\'' +
                ", winnendAantalLegs=" + winnendAantalLegs +
                ", legLijst=" + legLijst +
                ", stand=" + stand +
                '}';
    }
}