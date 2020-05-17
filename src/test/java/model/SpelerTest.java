package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpelerTest {

    private Speler speler1;
    private Speler speler2;


    @BeforeEach
    public void init() {
        speler1 = new Speler("voornaam1", "achternaam1", 19, "Beginner");
        speler2 = new Speler("voornaam2", "achternaam2", "Amateur");
    }

    @Test
    void getId() {
        assertEquals(0, speler1.getId());
        assertEquals(0, speler2.getId());
    }

    @Test
    void getVoornaam() {
        assertEquals("voornaam1", speler1.getVoornaam());
        assertEquals("voornaam2", speler2.getVoornaam());

    }

    @Test
    void getAchternaam() {
        assertEquals("achternaam1", speler1.getAchternaam());
        assertEquals("achternaam2", speler2.getAchternaam());
    }

    @Test
    void getLeeftijd() {
        assertEquals(19, speler1.getLeeftijd());
        assertEquals(0, speler2.getLeeftijd());
    }

    @Test
    void getNiveau() {
        assertEquals("Beginner", speler1.getNiveau());
        assertEquals("Amateur", speler2.getNiveau());
    }

    @Test
    void getSpellenGespeeld() {
        assertEquals(0, speler1.getSpellenGespeeld());
        assertEquals(0, speler2.getSpellenGespeeld());

    }

    @Test
    void getSpellenGewonnen() {
        assertEquals(0, speler1.getSpellenGewonnen());
        assertEquals(0, speler2.getSpellenGewonnen());
    }

    @Test
    void getSpellenVerloren() {
        assertEquals(0, speler1.getSpellenVerloren());
        assertEquals(0, speler2.getSpellenVerloren());
    }

    @Test
    void setId() {
        assertEquals(0, speler1.getId());
        assertEquals(0, speler2.getId());

        speler1.setId(22012001);
        speler2.setId(00000011);

        assertEquals(22012001,speler1.getId());
        assertEquals(00000011,speler2.getId());

    }

    @Test
    void setVoornaam() {
        assertEquals("voornaam1", speler1.getVoornaam());
        assertEquals("voornaam2", speler2.getVoornaam());

        speler1.setVoornaam("Lars");
        speler2.setVoornaam("Armin");

        assertEquals("Lars", speler1.getVoornaam());
        assertEquals("Armin", speler2.getVoornaam());
    }

    @Test
    void setAchternaam() {
        assertEquals("achternaam1", speler1.getAchternaam());
        assertEquals("achternaam2", speler2.getAchternaam());

        speler1.setAchternaam("Sterk");
        speler2.setAchternaam("achternaam5050");

        assertEquals("Sterk", speler1.getAchternaam());
        assertEquals("achternaam5050", speler2.getAchternaam());

    }

    @Test
    void setLeeftijd() {
        assertEquals(19, speler1.getLeeftijd());
        assertEquals(0, speler2.getLeeftijd());

        speler1.setLeeftijd(20);
        speler2.setLeeftijd(16);

        assertEquals(20, speler1.getLeeftijd());
        assertEquals(16, speler2.getLeeftijd());

    }

    @Test
    void setNiveau() {
        assertEquals("Beginner", speler1.getNiveau());
        assertEquals("Amateur", speler2.getNiveau());

        speler1.setNiveau("Amateur");
        speler2.setNiveau("Professional");

        assertEquals("Amateur", speler1.getNiveau());
        assertEquals("Professional", speler2.getNiveau());

    }

    @Test
    void setSpellenGespeeld() {
        assertEquals(0, speler1.getSpellenGespeeld());
        assertEquals(0, speler2.getSpellenGespeeld());

        speler1.setSpellenGespeeld(5);
        speler2.setSpellenGespeeld(4);

        assertEquals(5, speler1.getSpellenGespeeld());
        assertEquals(4, speler2.getSpellenGespeeld());
    }

    @Test
    void setSpellenGewonnen() {
        assertEquals(0, speler1.getSpellenGewonnen());
        assertEquals(0, speler2.getSpellenGewonnen());

        speler1.setSpellenGewonnen(4);
        speler2.setSpellenGewonnen(3);

        assertEquals(4, speler1.getSpellenGewonnen());
        assertEquals(3, speler2.getSpellenGewonnen());

    }

    @Test
    void setSpellenVerloren() {
        assertEquals(0, speler1.getSpellenVerloren());
        assertEquals(0, speler2.getSpellenVerloren());

        speler1.setSpellenVerloren(1);
        speler2.setSpellenVerloren(2);

        assertEquals(1, speler1.getSpellenVerloren());
        assertEquals(2, speler2.getSpellenVerloren());

    }
}