package model;

public class Speler {
    // Attributen
    private int id;
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private String niveau;
    private int spellenGespeeld;
    private int spellenGewonnen;
    private int spellenVerloren;


    // Constructor
    public Speler(int id, String voornaam, String achternaam, int leeftijd, String niveau) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.niveau = niveau;
    }

    // Methods
    public void maakSpeler() {

    }

    public void leesSpeler() {

    }

    public void updateSpeler() {

    }

    public void verwijderSpeler() {

    }

    @Override
    public String toString() {
        return "Speler{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", leeftijd=" + leeftijd +
                ", niveau='" + niveau + '\'' +
                '}';
    }
}