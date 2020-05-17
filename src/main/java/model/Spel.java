package model;

import java.util.ArrayList;

public class Spel {
    // --- Attributen ---
    private ArrayList<Speler> spelersLijst; // Associatie naar Speler
    private ArrayList<Leg> legLijst; // Associatie naar Leg
    private String type;
    private int[][] stand;
    private int winnendAantalLegs;

    // --- Constructor ---
    public Spel(ArrayList<Speler> spelersLijst, String type, int[][] stand, ArrayList<Leg> legLijst, int winnendAantalLegs) {
        this.spelersLijst = spelersLijst;
        this.type = type;
        this.stand = stand;
        this.legLijst = legLijst;
        this.winnendAantalLegs = winnendAantalLegs;
    }

    // --- Getters ---
    public ArrayList<Speler> getSpelersLijst() {
        return spelersLijst;
    }

    public String getType() {
        return type;
    }

    public int[][] getStand() {
        return stand;
    }

    public ArrayList<Leg> getLegLijst() {
        return legLijst;
    }

    public int getWinnendAantalLegs() {
        return winnendAantalLegs;
    }

    // --- Setters ---
    public void setSpelersLijst(ArrayList<Speler> spelersLijst) {
        this.spelersLijst = spelersLijst;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStand(int[][] stand) {
        this.stand = stand;
    }

    public void setLegLijst(ArrayList<Leg> legLijst) {
        this.legLijst = legLijst;
    }

    public void setWinnendAantalLegs(int winnendAantalLegs) {
        this.winnendAantalLegs = winnendAantalLegs;
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


}