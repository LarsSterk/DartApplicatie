package model;

import java.util.ArrayList;

public class Leg {
    // --- Attributen ---
    private int legId;
    private int worp;
    private ArrayList<Integer> scoreLijst;

    public Leg(ArrayList<Speler> spelersInLeg, String type) {
        this.legId = 0; // deze is nu hardcoded, word later geprogrammeerd op basis van de hoogste leg id
        this.worp = 1;
        this.scoreLijst = new ArrayList<>();

        // Bouw scorelijst op op basis van de hoeveelheid spelers.
        for (Speler speler : spelersInLeg) {
            if(type.equals("501")){
                scoreLijst.add(501);
            }
        }
    }

    // --- Getters ---
    public int getLegId() {
        return legId;
    }

    public int getWorp() {
        return worp;
    }

    public ArrayList<Integer> getScoreLijst() {
        return scoreLijst;
    }

    // --- Setters ---
    public void setLegId(int legId) {
        this.legId = legId;
    }

    public void setWorp(int worp) {
        this.worp = worp;
    }

    public void setScoreLijst(ArrayList<Integer> scoreLijst) {
        this.scoreLijst = scoreLijst;
    }

    // --- Methods ---
    public void nieuweLeg() {

    }

    public void updateLeg() {

    }

    public void verwijderLeg() {

    }

    public void nieuweWorp() {

    }

    public void updateWorp() {

    }

    public void verwijderWorp() {

    }

    public void voegScoreToe() {

    }

    public void verwijderScore() {

    }

    @Override
    public String toString() {
        return "Leg{" +
                "legId=" + legId +
                ", worp=" + worp +
                ", scoreLijst=" + scoreLijst +
                '}';
    }
}
