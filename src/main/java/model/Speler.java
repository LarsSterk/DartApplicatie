package model;

public class Speler {
    // Attributen
    private int id;
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private String niveau;

    // Constructor
    public Speler(int id, String voornaam, String achternaam, int leeftijd, String niveau) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.niveau = niveau;
    }

}
