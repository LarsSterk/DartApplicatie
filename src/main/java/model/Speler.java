package model;

import javax.servlet.annotation.WebListener;
import java.io.Serializable;

public class Speler implements Serializable {
    // --- Attributen ---
    private int id;
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private String niveau;
    private int spellenGespeeld;
    private int spellenGewonnen;
    private int spellenVerloren;


    // --- Constructor ---
    public Speler(int id, String voornaam, String achternaam, int leeftijd, String niveau) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.niveau = niveau;
        this.id = id;
        this.spellenGespeeld = 0;
        this.spellenGewonnen = 0;
        this.spellenVerloren = 0;
    }

    public Speler(int id, String voornaam, String achternaam, String niveau) { // Zonder leeftijd
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.niveau = niveau;
        this.id = id;
        this.spellenGespeeld = 0;
        this.spellenGewonnen = 0;
        this.spellenVerloren = 0;
    }

    // --- Getters ---
    public int getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public String getNiveau() {
        return niveau;
    }

    public int getSpellenGespeeld() {
        return spellenGespeeld;
    }

    public int getSpellenGewonnen() {
        return spellenGewonnen;
    }

    public int getSpellenVerloren() {
        return spellenVerloren;
    }

    // --- Setters ---
    public void setId(int id) {
        this.id = id;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setSpellenGespeeld(int spellenGespeeld) {
        this.spellenGespeeld = spellenGespeeld;
    }

    public void setSpellenGewonnen(int spellenGewonnen) {
        this.spellenGewonnen = spellenGewonnen;
    }

    public void setSpellenVerloren(int spellenVerloren) {
        this.spellenVerloren = spellenVerloren;
    }
}