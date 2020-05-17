package model;

public class Speler {
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
    public Speler(String voornaam, String achternaam, int leeftijd, String niveau) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.niveau = niveau;
        this.id = 0;
        this.spellenGespeeld = 0;
        this.spellenGewonnen = 0;
        this.spellenVerloren = 0;
    }

    public Speler(String voornaam, String achternaam, String niveau) { // Zonder leeftijd
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.niveau = niveau;
        this.id = 0;
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

    // --- Methods ---
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